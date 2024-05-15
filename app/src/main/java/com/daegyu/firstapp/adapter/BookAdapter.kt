import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daegyu.firstapp.databinding.ItemBookBinding
import com.daegyu.firstapp.model.Book

// 데이터를 뷰홀더에 담아 리사이클러뷰에 전달하는 역할 수행
class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var list: List<Book> = listOf()

    fun setList(list: List<Book>) {
        this.list = list
        // 데이터 변경을 알려 UI 업데이트 (경고 : 데이터가 변경되지 않은 아이템도 모두 업데이트하기 때문)
        notifyDataSetChanged()
    }

    // 재사용을 위한 뷰홀더
    inner class BookViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
        // 아이템을 통해 데이터 바인딩
        fun bind(item: Book) {
            binding.textTitle.text = item.title
            binding.textAuthor.text = item.author

            // 이미지 로딩 라이브러리
            Glide.with(binding.root)
                .load(item.cover)
                .into(binding.imageCover)
        }
    }

    // 바인딩 객체를 생성하여 뷰홀더로 감싸 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return list.size
    }

    // 뷰홀더 데이터 바인딩
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        // 인덱스를 통해 리스트에서 아이템을 가져와 데이터 바인딩
        holder.bind(list[position])
    }
}