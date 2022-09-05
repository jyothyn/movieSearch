package com.examples.moviesearch.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieType(
    @SerializedName("Title") val title: String = "",
    @SerializedName("Year") val year: String = "",
    @SerializedName("imdbID") val id: String = "",
    @SerializedName("Type") val type: String = "",
    @SerializedName("Poster") val poster: String = ""
) : Serializable {
    override fun toString(): String {
        return "Year: " + year + ", " +
                "imdbID: " + id + ", " +
                "Type: " + type + "."
    }
}