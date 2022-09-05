package com.examples.moviesearch.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("Search") val moviesList: List<MovieType>? = null
)
