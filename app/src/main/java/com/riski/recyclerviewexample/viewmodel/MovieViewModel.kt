package com.riski.recyclerviewexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riski.recyclerviewexample.data.remote.api.ApiConfig
import com.riski.recyclerviewexample.data.remote.response.MovieResponse
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    fun getMovies(): LiveData<List<MoviesItem>> {
        val movies = MutableLiveData<List<MoviesItem>>()
        val client = ApiConfig.getApiService().getMovie()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                movies.value = response.body()?.results as List<MoviesItem>
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MovieViewModel", t.message.toString())
            }
        })
        return movies
    }

    fun getMovieDetail(movieId: Int): LiveData<MoviesItem> {
        val detailMovie = MutableLiveData<MoviesItem>()
        val client = ApiConfig.getApiService().getMovieById(movieId)

        client.enqueue(object : Callback<MoviesItem> {
            override fun onResponse(call: Call<MoviesItem>, response: Response<MoviesItem>) {
                detailMovie.value = response.body() as MoviesItem
            }

            override fun onFailure(call: Call<MoviesItem>, t: Throwable) {
                Log.e("MovieViewModel", t.message.toString())
            }
        })
        return detailMovie
    }
}