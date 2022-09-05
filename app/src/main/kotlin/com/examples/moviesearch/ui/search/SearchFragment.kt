package com.examples.moviesearch.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.examples.moviesearch.R
import com.examples.moviesearch.databinding.FragmentSearchBinding
import com.examples.moviesearch.model.MovieType
import com.examples.moviesearch.viewmodel.MainViewModel
import kotlinx.coroutines.launch

//@AndroidEntryPoint
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

        lifecycleScope.launch { //lifecycleScope.launchWhenResumed {
            viewModel.movieListResult.collect {
                binding.movieListView.adapter = MovieItemRecyclerViewAdapter(it, ::openMovie)
            }
        }
        lifecycleScope.launch {
            viewModel.noResults.collect {
                binding.noResults.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                query.let {
                    viewModel.handleSearch(it)
                }
                searchView.clearFocus()
                return true
            }
        })
        return root
    }

    private fun openMovie(movie: MovieType) {
//        val direction = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
//        Navigation.findNavController(view).navigate(direction)
        //OR
//        Navigation.findNavController(view).navigate(
//            R.id.action_navigation_search_to_detailFragment, bundle)
        val bundle = bundleOf("selectedMovie" to movie)
        findNavController().navigate(R.id.action_navigation_search_to_detailFragment, bundle)
    }

}