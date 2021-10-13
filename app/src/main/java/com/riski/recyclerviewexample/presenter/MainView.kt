package com.riski.recyclerviewexample.presenter

import com.riski.recyclerviewexample.data.remote.response.MovieResponse
import com.riski.recyclerviewexample.data.remote.response.MoviesItem

interface MainView: BaseView {
    fun onSuccess(data: List<MoviesItem>?)
    fun onFailure()
}