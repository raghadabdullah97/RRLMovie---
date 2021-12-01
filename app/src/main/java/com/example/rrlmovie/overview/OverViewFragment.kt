package com.example.rrlmovie.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.rrlmovie.MovieAdapter
import com.example.rrlmovie.R
import com.example.rrlmovie.databinding.FragmentOverViewBinding
import com.example.rrlmovie.network.ResultsItem

class OverViewFragment: Fragment() {

private val viewModel: MovieVeiwModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    // Inflate the layout for this fragment
    val binding = FragmentOverViewBinding.inflate(inflater)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
//       binding.photosGrid.adapter = com.example.contriesflagapi.PhotoGridAdapter()
    binding.photosGrid.adapter = MovieAdapter()
    return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.genres_menu, menu)
        val actionButton = menu.findItem(R.id.option_12)
        val animmationButton = menu.findItem(R.id.option_16)
        val dramaButton = menu.findItem(R.id.option_18)
        val crimeButton = menu.findItem(R.id.option_80)
    }

    private fun setMovieBasedOnGener(menuItem: MenuItem?){
        if (menuItem == null) {return}
    }

//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.option_18 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.DRAMA)
            R.id.option_16 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ANIMATION)
            R.id.option_80 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.CRIME)
            R.id.option_12 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ACTION)
            R.id.top_rated -> viewModel.getMovieList("top_rated")
            R.id.popular -> viewModel.getMovieList("popular")
            R.id.latest -> viewModel.getMovieList("latest")
            R.id.now_playing -> viewModel.getMovieList("now_playing")
            R.id.upcoming -> viewModel.getMovieList("upcoming")
           else -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ACTION, 1)
        }

    return true
    }
}

