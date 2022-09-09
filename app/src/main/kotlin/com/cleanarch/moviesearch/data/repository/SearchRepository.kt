package com.cleanarch.moviesearch.data.repository

import com.cleanarch.moviesearch.data.model.MovieSearchResult

interface SearchRepository {

//    suspend fun submitQuery(query: String):
//            Flow<MovieSearchResult>

    suspend fun submitSearch(query: String): MovieSearchResult
}