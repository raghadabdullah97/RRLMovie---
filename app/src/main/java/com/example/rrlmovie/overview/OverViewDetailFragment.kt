package com.example.rrlmovie.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.rrlmovie.R
import com.example.rrlmovie.databinding.FragmentOverViewDetailBinding
const val  movieStie = "https://www.themoviedb.org/movie/"
class OverViewDetailFragment : Fragment() {


    var binding : FragmentOverViewDetailBinding? = null
    private val viewModel: MovieVeiwModel by activityViewModels()

    /**
     * recived the wanted movie id to show
     */
    private var param: Int = 0 // before start
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getInt("id")

        }
    }
    /**
     * connect its own view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentOverViewDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }
    /**
     * conncet other classes and the functionality of data binding
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.movieVeiwModel = viewModel
        binding?.overViewDetailFragment = this@OverViewDetailFragment

        viewModel.getMovieInfo(param)
    }

    /**
     * go to the movie page based on its id
     */
    fun  onClickGotosite(value:Int){
        val queryUrl: Uri = Uri.parse("${movieStie}${value}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        context?.startActivity(intent)
    }
}