package com.example.rrlmovie.overview


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rrlmovie.network.GenersData
import com.example.rrlmovie.network.GenresItem
import com.example.rrlmovie.network.MovieApi
import com.example.rrlmovie.network.ResultsItem
import kotlinx.coroutines.launch
import java.lang.Exception

var gener = 0
enum class MoviesApiStatus { LOADING, ERROR, DONE }



class MovieVeiwModel : ViewModel() {

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    val movieTitle = MutableLiveData<String>()

    val movieDetail = MutableLiveData<String>()

    val moviePoster = MutableLiveData<String>()
     var movieGener:MutableList<List<Int>?> = mutableListOf()

    private val _photos = MutableLiveData<List<ResultsItem?>?>()
    val photos: LiveData<List<ResultsItem?>?> = _photos

    private val _movieList = MutableLiveData<List<ResultsItem?>>()
    val movieList: LiveData<List<ResultsItem?>> = _movieList



//    private val _photos = MutableLiveData<String>()
//    val photos: LiveData<String> = _photos
    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val listResult = MovieApi.retrofitService.getMovieList(3).results
                _photos.value = listResult
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _photos.value = listOf()

            }
        }
    }

    private fun getMovieGenersList(filter: MovieApiFilter) {

        viewModelScope.launch {
           val listOfGeners = MovieApi.retrofitService.getMovieGenersList(filter.generId).results
            _movieList.value = listOfGeners

        }
    }
    enum class MovieApiFilter(val generId: Int ) {
        ACTION(12),
        ANIMATION(16),
        DRAMA(17),
        CRIME(80), }

    fun updateFilter(filter: MovieApiFilter) {
        getMovieGenersList(filter)
    }


//        fun addToList(i : Int){
//         viewModelScope.launch {
//         val listResult = MovieApi.retrofitService.getMovieList().results
//        _photos.value?.plus(listResult?.get(i))}
//    }

    fun clearList(){
        _photos.value = listOf()
    }

     fun getMovieInfo(index: Int) {
        val item = _photos.value?.get(index)
        moviePoster.value = item?.posterPath
        movieTitle.value = item?.originalTitle
        movieDetail.value = item?.overview

    }
}