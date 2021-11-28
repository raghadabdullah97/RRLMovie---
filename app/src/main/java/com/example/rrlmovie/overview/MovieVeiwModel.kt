package com.example.rrlmovie.overview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rrlmovie.network.MovieApi
import com.example.rrlmovie.network.ResultsItem
import kotlinx.coroutines.launch
import java.lang.Exception


enum class MoviesApiStatus { LOADING, ERROR, DONE }

class MovieVeiwModel : ViewModel() {

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    val movieTitle = MutableLiveData<String>()

    val movieDetail = MutableLiveData<String>()

    val moviePoster = MutableLiveData<String>()


    private val _photos = MutableLiveData<List<ResultsItem?>?>()
    val photos: LiveData<List<ResultsItem?>?> = _photos

//    private val _photos = MutableLiveData<String>()
//    val photos: LiveData<String> = _photos


    init {
        getMovieList()
    }


    private fun getMovieList() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
            try {
                val listResult = MovieApi.retrofitService.getMovieList().results
                _photos.value = listResult
                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _photos.value = listOf()

            }
        }
    }

     fun getMovieInfo(index: Int) {
        val item = _photos.value?.get(index)

        moviePoster.value = item?.posterPath
        movieTitle.value = item?.originalTitle
        movieDetail.value = item?.overview

    }




}