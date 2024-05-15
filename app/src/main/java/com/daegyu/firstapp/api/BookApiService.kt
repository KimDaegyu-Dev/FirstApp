package com.daegyu.firstapp.api

import com.daegyu.firstapp.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("api/ItemSearch.aspx")
    fun search(
        @Query("Query") keyword: String,
        @Query("TTBKey") ttbkey: String = "ttbhongmuchae0539001",
        @Query("QueryType") queryType: String = "Keyword",
        @Query("MaxResults") maxResults: Int = 50,
        @Query("Start") page: Int = 1,
        @Query("SearchTarget") target: String = "Book",
        @Query("Output") output: String = "JS",
        @Query("Version") version: String = "20131101",
    ): Call<BookResponse>
}