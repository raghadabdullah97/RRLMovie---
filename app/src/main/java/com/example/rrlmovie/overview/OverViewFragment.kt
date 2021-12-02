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
    /**
     * by activity view models used .....
     */
private val viewModel: MovieVeiwModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make the options menu available
        setHasOptionsMenu(true)
    }

    /**
     * make the connection with the view and other classes
     */
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    // Inflate the layout for this fragment
    val binding = FragmentOverViewBinding.inflate(inflater)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
//       make the the connection with the adapter
    binding.photosGrid.adapter = MovieAdapter()
    return binding.root
    }

    /**
     * the below for future uses
     */
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

    /**
     * here click listener for the menu buttons
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.option_18 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.DRAMA)
            R.id.option_16 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ANIMATION)
            R.id.option_80 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.CRIME)
            R.id.option_12 -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ACTION)
            R.id.top_rated -> viewModel.getMovieList("top_rated")
            R.id.popular -> viewModel.getMovieList("popular")
            R.id.latest -> viewModel.getMovieList("upcoming")
            R.id.now_playing -> viewModel.getMovieList("now_playing")
            R.id.upcoming -> viewModel.getMovieList("upcoming")
           R.id.showAll -> viewModel.updateFilter(MovieVeiwModel.MovieApiFilter.ACTION, 1)
        }

    return true
    }
}

