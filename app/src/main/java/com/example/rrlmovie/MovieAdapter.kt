package com.example.rrlmovie
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rrlmovie.databinding.GridViewItemBinding
import com.example.rrlmovie.network.ResultsItem
import com.example.rrlmovie.overview.OverViewFragment
import com.example.rrlmovie.overview.OverViewFragmentDirections

class MovieAdapter : ListAdapter<ResultsItem, MovieAdapter.MoviePhotosViewHolder>(DiffCallback)  {
    /**
     * prepare the positions of the views
     */
    class MoviePhotosViewHolder(var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviePhoto: ResultsItem) {
            binding.item = moviePhoto
            binding.executePendingBindings()
        }
        var myImage = binding.myImage
    }
    /**
     * check the new and the old data taken from the API
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }
    /**
     * on creationg the viewHolder takes the views from the [GridViewItemBinding]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): MoviePhotosViewHolder {
        return MoviePhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }
    /**
     * binding the list of data to the item view
     */
    override fun onBindViewHolder(holder: MoviePhotosViewHolder, position: Int) {
        val moviePhoto = getItem(position)
        holder.bind(moviePhoto)
        holder.myImage.setOnClickListener {
            val action = OverViewFragmentDirections.actionOverViewFragmentToOverViewDetailFragment(position)
            holder.itemView.findNavController().navigate(action)
        }
    }
}