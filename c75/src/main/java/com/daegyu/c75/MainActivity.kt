package com.daegyu.c75

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.daegyu.c75.DetailActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // API 33부터 알림 권한 요청을 위한 런처
        val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if(granted) { // 권한이 부여된 경우
                showNotification() // 알림 표시
            } else {
                Toast.makeText(this, "알림 궈한을 허용해주세요!", Toast.LENGTH_SHORT).show()
            }
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // 알림 권한 요청
                permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            } else {
                showNotification() // 알림표시
            }
        }
    }

    private fun showNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            //채널에 다양한 정보 설정
            channel.description = "My Channel One Description"
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelId)
        } else {
            builder = NotificationCompat.Builder(this)
        }


        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Content Title")
        builder.setContentText("Content Massage")

        val intent = Intent(this, DetailActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 10, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE) // 여기도 버전 문제로 플래그 추가
        builder.setContentIntent(pendingIntent)

        val actionIntent = Intent(this, DetailActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(this, 20,
            actionIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE) // 여기도 버전 문제로 플래그 추가
        builder.addAction(
            NotificationCompat.Action.Builder(
                android.R.drawable.stat_notify_more,
                "Action",
                actionPendingIntent
            ).build()
        )

        val bigPicture= BitmapFactory.decodeResource(resources, R.drawable.logo_1)
        val bigStyle = NotificationCompat.BigPictureStyle()
        bigStyle.bigPicture(bigPicture)
        builder.setStyle(bigStyle)

        manager.notify(11, builder.build())
    }
}