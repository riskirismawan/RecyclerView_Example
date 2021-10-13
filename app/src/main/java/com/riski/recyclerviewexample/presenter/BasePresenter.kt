package com.riski.recyclerviewexample.presenter

interface BasePresenter<in T : BaseView> {

    fun onAttach(view : T)
    fun onDettach()
}