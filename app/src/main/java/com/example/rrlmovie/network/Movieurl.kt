
package com.example.rrlmovie.network

import com.example.rrlmovie.network.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org"
private const val generalList = "/genre/movie/list"

private const val API_KEY = "a45bd525d7f3152d793dd5b9e91d8074"

// for using the moshi library
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// for using retroit library
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// for getting the data from the url

interface Movieurls {
    @GET("/3/movie/{sort}?api_key=${API_KEY}&language=en-US")
    suspend fun getMovieList(@Path("sort") sort: String) : Response
     @GET("/3/discover/movie?api_key=${API_KEY}")
     suspend fun getMovieGenersList(@Query("with_genres") genreId: Int) : Response

//    suspend fun getMovieList(@path("page") page: Int) : Response
    /**
     * ============================ the path method is below =============================
     */
//    @GET("/3/movie/{id}?api_key=${API_KEY}&language=en-US")
//    suspend fun getMovieList(@path("id") movieID: Int) : ItemResults
}

object MovieApi {
    val retrofitService: Movieurls by lazy {
        retrofit.create(Movieurls::class.java)
    }
}