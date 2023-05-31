package com.example.elclarin

data class Noticias(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)