package com.example.newsaggregator

data class Article(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
)

data class NewsResponse(
    val articles: List<Article>
)