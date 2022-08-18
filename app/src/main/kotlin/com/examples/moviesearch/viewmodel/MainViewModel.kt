package com.examples.moviesearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.moviesearch.model.MovieSearchResult
import java.util.*

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = getGreetingMessage()
    }
    val text: LiveData<String> = _text

    private fun getGreetingMessage(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning!"
            in 12..15 -> "Good Afternoon!"
            in 16..20 -> "Good Evening!"
            in 21..23 -> "Good Night!"
            else -> "Hello User"
        }
    }

    var mSearchResult = MutableLiveData<MovieSearchResult>()
    var mSearchString: String = ""

    fun clearSearch() {
        mSearchString = ""
        mSearchResult.value = MovieSearchResult()
    }
}