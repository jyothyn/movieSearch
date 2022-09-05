package com.examples.moviesearch.network

import com.examples.moviesearch.model.MovieSearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
//    suspend fun submitQuery(url: String, query: String, i: String?="tt3896198", key: String?="6d53ef23"):
//            Flow<MovieSearchResult>

    suspend fun submitQuery(query: String):
            Flow<MovieSearchResult>
}