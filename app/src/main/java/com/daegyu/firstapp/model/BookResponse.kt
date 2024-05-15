package com.daegyu.firstapp.model

data class BookResponse(
    val item: List<Book>?,
)

data class Book(
    val title: String?,
    val link: String?,
    val author: String?,
    val pubDate: String?,
    val description: String?,
    val isbn: String?,
    val publisher: String?,
    var cover: String?,
)