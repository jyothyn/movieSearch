package com.examples.moviesearch.domain.entity

import java.io.Serializable

// Is Serializable or Parcelable to pass it as navArgs
data class Movie(
    val id: String = "",
    val title: String = "",
    val year: String = "",
    val type: String = "",
    val poster: String = ""
) : Serializable {
    fun asDisplayText(): String {
        return "Year: " + year + ", " +
                "imdbID: " + id + ", " +
                "Type: " + type + "."
    }
}