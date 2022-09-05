package com.examples.moviesearch.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.moviesearch.R
import com.examples.moviesearch.model.MovieType

typealias MovieItemClickListener = (MovieType) -> Unit

class MovieItemRecyclerViewAdapter(
    private val movies: List<MovieType>,
    private val listener: MovieItemClickListener
) : RecyclerView.Adapter<MovieItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.bindVals(item)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val idView: TextView = view.findViewById(R.id.movie_item_number)
        private val contentView: TextView = view.findViewById(R.id.movie_content)

        fun bindVals(item: MovieType) {
            idView.text = item.title
            contentView.text = item.toString()
            itemView.setOnClickListener {
                listener(item)
            }
        }

    }
}