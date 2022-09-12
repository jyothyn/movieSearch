package com.cleanarch.moviesearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarch.moviesearch.data.ds.LaunchTimeDS
import com.cleanarch.moviesearch.domain.State
import com.cleanarch.moviesearch.domain.TitleUseCase
import com.cleanarch.moviesearch.domain.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val repository: SearchRepository,
    private val titleUseCase: TitleUseCase,
//    private val playlistDs: PlaylistDs,
    private val launchTimeDS: LaunchTimeDS
) : ViewModel() {

    private val _greetingMsg = MutableStateFlow("Hello User!")
    val greetingMsg: StateFlow<String>
        get() = _greetingMsg

    private val _lastLaunchAt = MutableStateFlow("just now")
    val lastLaunchAt: StateFlow<String>
        get() = _lastLaunchAt


    init {
        _greetingMsg.value = getGreetingMessage()
        updateLaunchTime()
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

    private fun updateLaunchTime() {
        viewModelScope.launch {
            _lastLaunchAt.value = launchTimeDS.lastLaunchTime.first()
            launchTimeDS.setLaunchTime()
        }
    }

    private val _searchResult = MutableStateFlow<List<Movie>>(emptyList())
    val movieListResult: StateFlow<List<Movie>>
        get() = _searchResult
    val noResults = MutableStateFlow(false)

    private var mSearchString: String = ""
    fun clearSearch() {
        mSearchString = ""
//        _searchResult.value = emptyList()
    }

    fun handleSearch(str: String) {
        mSearchString = str
//        searchWithQuery()
        //call domain usecase
        searchTitles()
    }

//    private fun searchWithQuery() = viewModelScope.launch {
//        repository.submitQuery(mSearchString).collect { values ->
//            _searchResult.value = values.moviesList ?: emptyList()
//            noResults.value = values.moviesList?.isEmpty() ?: true
//        }
//    }

//    private suspend fun getSearchState(query: String): Flow<State<List<Movie>>> = flow {
//        emit(State.LoadingState)
//        try {
//            emit(State.DataState(titleUseCase(query)))
//        } catch (e: Exception) {
//            emit(State.ErrorState(e))
//        }
//    }

    private fun searchTitles() {
        viewModelScope.launch {
            titleUseCase(mSearchString).collect {
//            getSearchState(mSearchString).collect {
                when (it) {
                    is State.DataState -> {
                        _searchResult.value = it.data
                        noResults.value = it.data.isEmpty()
                    }
                    else -> {
                        _searchResult.value = emptyList()
                        noResults.value = true
                    }
                }
            }
        }
    }
}
