package com.example.rrlmovie

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.rrlmovie.network.ResultsItem
import com.example.rrlmovie.overview.MoviesApiStatus

/**
 * assign custom bindingn to the view to translated the data taken from the API to the required Illustrate
 */
@BindingAdapter("photoUrl")
fun ImageView.bind(photoUrl: String?) {
    Log.e("tag", "Starthere")
    photoUrl?.let {
        val photoUri =photoUrl.toUri().buildUpon().build()
        Glide.with(this.context)
            .load("https://image.tmdb.org/t/p/w500/${photoUri}")
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(this)
    }
}

/**
 * update the view on the main view based on the status predicted
 */
@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MoviesApiStatus?){
    when (status){
        MoviesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.DONE ->{
            statusImageView.visibility = View.GONE
        }
        MoviesApiStatus.ERROR ->{
            statusImageView.visibility = View.GONE
            statusImageView.setImageResource(R.drawable.ic_baseline_broken_image_24)
        }
    }
}
/**
 * the method updates the wnated view in the recycler view
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}