package com.example.rrlmovie.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.rrlmovie.MovieAdapter
import com.example.rrlmovie.databinding.FragmentOverViewBinding

class OverViewFragment: Fragment() {

private val viewModel: MovieVeiwModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    val binding = FragmentOverViewBinding.inflate(inflater)
    binding.lifecycleOwner = this
    binding.viewModel = viewModel
//       binding.photosGrid.adapter = com.example.contriesflagapi.PhotoGridAdapter()
    binding.photosGrid.adapter = MovieAdapter()
    return binding.root
}


}