package com.riski.recyclerviewexample.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.riski.recyclerviewexample.databinding.ActivityMainBinding
import com.riski.recyclerviewexample.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        viewModel.getMovies().observe(this, {movieItem ->
            adapter.setItems(movieItem)
        })
    }
}