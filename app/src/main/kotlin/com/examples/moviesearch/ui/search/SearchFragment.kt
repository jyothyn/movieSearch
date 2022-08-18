package com.examples.moviesearch.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.examples.moviesearch.databinding.FragmentSearchBinding
import com.examples.moviesearch.viewmodel.MainViewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.clearSearch()
        val searchView = binding.movieSearch
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                println("get suggestion for:" + newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                println("submit search:" + query)
                query.let {
//                    viewModel.handleSearch(it)
                }
                searchView.clearFocus()
                return true
            }
        })
        return root
    }
}