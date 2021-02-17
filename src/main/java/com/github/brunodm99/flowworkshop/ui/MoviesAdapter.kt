package com.github.brunodm99.flowworkshop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.brunodm99.flowworkshop.R
import com.github.brunodm99.flowworkshop.data.domain.Movie
import com.github.brunodm99.flowworkshop.databinding.ViewMovieBinding
import com.github.brunodm99.flowworkshop.ui.common.collectFlow
import com.github.brunodm99.flowworkshop.ui.common.onClickEvents
import com.github.brunodm99.flowworkshop.ui.common.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MoviesAdapter(private val scope: CoroutineScope) :
    ListAdapter<Movie, MoviesAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        scope.collectFlow(itemView.onClickEvents) {
            itemView.context.toast(item.title)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewMovieBinding.bind(itemView)

        fun bind(item: Movie) = with(binding) {
            movieTitle.text = item.title
            Glide
                .with(movieCover.context)
                .load("https://image.tmdb.org/t/p/w185/${item.posterPath}")
                .into(movieCover)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
}