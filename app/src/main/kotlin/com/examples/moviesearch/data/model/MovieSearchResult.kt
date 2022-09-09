package com.examples.moviesearch.data.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("Search") val moviesList: List<MovieType>? = null,
    @SerializedName("Response") val response: String? = null,
    @SerializedName("Error") val error: String? = null
)
