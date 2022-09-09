package com.cleanarch.moviesearch.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cleanarch.moviesearch.databinding.FragmentDetailBinding

//@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        args.selectedMovie?.let {
            binding.movieTitle.text = it.title
            binding.year.text = it.year
            binding.type.text = it.type
            try {
                Glide.with(this)
                    .load(it.poster)
                    .into(binding.imageView)
            } catch (e: Exception) {
                println("${it.poster} ERROR: ${e.message}")
                activity?.applicationContext?.let { appCntxt ->
                    Toast.makeText(appCntxt, "Image could not be loaded", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}