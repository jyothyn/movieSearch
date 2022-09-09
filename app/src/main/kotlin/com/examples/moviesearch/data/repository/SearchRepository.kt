package com.examples.moviesearch.data.repository

import com.examples.moviesearch.data.model.MovieSearchResult

interface SearchRepository {
//    suspend fun submitQuery(url: String, query: String, i: String?="tt3896198", key: String?="6d53ef23"):
//            Flow<MovieSearchResult>

//    suspend fun submitQuery(query: String):
//            Flow<MovieSearchResult>

    suspend fun submitSearch(query: String): MovieSearchResult
}