package com.examples.moviesearch.model

data class MovieSearchResult(val moviesList: List<MovieType>?=null)

data class MovieType(
    val title: String,
    val year: String,
    val id: String,
    val type: String,
    val poster: String
)