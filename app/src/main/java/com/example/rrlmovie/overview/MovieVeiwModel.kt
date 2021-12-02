package com.example.rrlmovie.overview


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

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

    private val _movieInfo = MutableLiveData<List<ResultsItem?>?>()
    val movieInfo: LiveData<List<ResultsItem?>?> = _movieInfo

    private val _movieList = MutableLiveData<List<ResultsItem?>>()
    val movieList: LiveData<List<ResultsItem?>> = _movieList



//    private val _photos = MutableLiveData<String>()
//    val photos: LiveData<String> = _photos
    init {
        getMovieList()
    }

     fun getMovieList(type: String = "upcoming") {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val listResult = MovieApi.retrofitService.getMovieList(type).results
                _movieInfo.value = listResult
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _movieInfo.value = listOf()

            }
        }
    }

    /**
     * take the list bases on the quer input
     */
     fun getMovieGenersList(filter: MovieApiFilter) {
//        viewModelScope.launch {
//           val listOfGeners = MovieApi.retrofitService.getMovieGenersList(filter.generId).results
//            _movieList.value = listOfGeners
         viewModelScope.launch {
             _status.value = MoviesApiStatus.LOADING
             try {
                 val listResult = MovieApi.retrofitService.getMovieGenersList(filter.generId).results
                 _movieInfo.value = listResult
                 _status.value = MoviesApiStatus.DONE
             } catch (e: Exception) {
                 _status.value = MoviesApiStatus.ERROR
                 _movieInfo.value = listOf()

             }
         }
    }

//    fun getMovieSortedBy(type: String){
//        viewModelScope.launch {
//            _status.value = MoviesApiStatus.LOADING
//            try {
//                val listResult = MovieApi.retrofitService.sortMovieList(filter.generId).results
//                _photos.value = listResult
//                _status.value = MoviesApiStatus.DONE
//            } catch (e: Exception) {
//                _status.value = MoviesApiStatus.ERROR
//                _photos.value = listOf()
//
//            }
//        }
//    }

    /**
     * constant geners based on the website
     */
    enum class MovieApiFilter(val generId: Int ) {
        ACTION(12),
        ANIMATION(16),
        DRAMA(18),
        CRIME(80), }
    /**
     * function called from the main fragment that connacted to the menu button
     * and send it to  getMovieGenersList function
     */
    fun updateFilter(filter: MovieApiFilter, x:Int = 0 ) {
        if (x ==1){ getMovieList()}
        else {
      getMovieGenersList(filter)}

    }


//        fun addToList(i : Int){
//         viewModelScope.launch {
//         val listResult = MovieApi.retrofitService.getMovieList().results
//        _photos.value?.plus(listResult?.get(i))}
//    }

    fun clearList(){
        _movieInfo.value = listOf()
    }

     fun getMovieInfo(index: Int) {
        val item = _movieInfo.value?.get(index)
        moviePoster.value = item?.posterPath
        movieTitle.value = item?.originalTitle
        movieDetail.value = item?.overview

    }
}