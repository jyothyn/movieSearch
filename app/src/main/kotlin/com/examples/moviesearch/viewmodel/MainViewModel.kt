package com.examples.moviesearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesearch.model.MovieType
import com.examples.moviesearch.network.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _greetingMsg = MutableStateFlow("Hello User!")
    val greetingMsg: StateFlow<String>
        get() = _greetingMsg

    init {
        _greetingMsg.value = getGreetingMessage()
    }

    private fun getGreetingMessage(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning!"
            in 12..17 -> "Good Afternoon!"
            in 18..20 -> "Good Evening!"
            in 21..23 -> "Good Night!"
            else -> "Hello User"
        }
    }

    private val _searchResult = MutableStateFlow<List<MovieType>>(emptyList())
    val movieListResult: StateFlow<List<MovieType>>
        get() = _searchResult
    val noResults = MutableStateFlow(false)

    private var mSearchString: String = ""
    fun clearSearch() {
        mSearchString = ""
//        _searchResult.value = emptyList()
    }

    fun handleSearch(str: String) {
        mSearchString = str
        searchWithQuery()
    }

    private fun searchWithQuery() = viewModelScope.launch {
        repository.submitQuery(mSearchString).onEach { values ->
            _searchResult.value = values.moviesList ?: emptyList()
            noResults.value = values.moviesList?.isEmpty() ?: true
        }.launchIn(viewModelScope)
    }
}