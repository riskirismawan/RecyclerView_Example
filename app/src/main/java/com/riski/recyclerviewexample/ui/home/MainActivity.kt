package com.riski.recyclerviewexample.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.riski.recyclerviewexample.data.remote.response.MovieResponse
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import com.riski.recyclerviewexample.databinding.ActivityMainBinding
import com.riski.recyclerviewexample.presenter.MainPresenter
import com.riski.recyclerviewexample.presenter.MainView
import com.riski.recyclerviewexample.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var viewModel: MovieViewModel
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter()

        Log.e("MainActivity", "onCreate: ${presenter.getData()}")

        adapter = ItemAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

//        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
//        viewModel.getMovies().observe(this, {movieItem ->
//            adapter.setItems(movieItem)
//        })
    }

    override fun onSuccess(data: List<MoviesItem>?) {
        if (data != null) {
            adapter.setItems(data)
        }
    }

    override fun onFailure() {
        Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
        Log.e("MainActivity", "onFailure: Failed get data")
    }

    override fun onAttachView() {
        presenter.onAttach(this)
        presenter.getData()
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        onAttachView()
        super.onStart()
    }

    override fun onStop() {
        onDettachView()
        super.onStop()
    }
}