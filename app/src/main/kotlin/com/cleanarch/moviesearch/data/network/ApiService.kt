package com.cleanarch.moviesearch.data.network

import com.cleanarch.moviesearch.data.model.MovieSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://www.omdbapi.com"

interface ApiService {
//    @GET
//    suspend fun submitQuery(
//        @Url url: String? = BASE_URL,
//        @Query("i") i: String? = "tt3896198",
//        @Query("apikey") k: String? = "6d53ef23",
//        @Query("s") s: String
//    ): MovieSearchResult


    @GET("$BASE_URL?i=tt3896198&apikey=6d53ef23")
    suspend fun submitQuery(@Query("s") s: String): MovieSearchResult
}