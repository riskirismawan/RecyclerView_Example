package com.riski.recyclerviewexample.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.riski.recyclerviewexample.data.remote.api.ApiConfig
import com.riski.recyclerviewexample.data.remote.response.MovieResponse
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter: BasePresenter<MainView> {
    var modelView: MainView? = null

    override fun onAttach(view: MainView) {
        modelView = view
    }

    fun getData() {
        val client = ApiConfig.getApiService().getMovie()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                modelView?.onSuccess(response.body()!!.results)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                modelView?.onFailure()
            }
        })
    }

    fun getDataById(movieId: Int) {
        val client = ApiConfig.getApiService().getMovieById(movieId)

        client.enqueue(object : Callback<MoviesItem> {
            override fun onResponse(call: Call<MoviesItem>, response: Response<MoviesItem>) {
                modelView?.onSuccess(listOf(response.body()!!))
            }

            override fun onFailure(call: Call<MoviesItem>, t: Throwable) {
                modelView?.onFailure()
            }

        })
    }

    override fun onDettach() {
        modelView = null
    }
}