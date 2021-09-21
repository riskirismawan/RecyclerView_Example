package com.riski.recyclerviewexample.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.riski.recyclerviewexample.databinding.ActivityDetailBinding
import com.riski.recyclerviewexample.viewmodel.MovieViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        val movieid = intent.extras?.getInt(DATA)

        if (movieid != null) {
            viewModel.getMovieDetail(movieid).observe(this, { movie ->
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load("https://image.tmdb.org/t/p/original${movie.posterPath}")
                        .into(imgPhoto)

                    tvUserScoreValue.text = movie.voteAverage.toString()
                    tvBudgetValue.text = if (movie.revenue == 0) {
                        "-"
                    } else {
                        "\$${movie.budget}"
                    }
                    tvRevenueValue.text = if (movie.revenue == 0) {
                        "-"
                    } else {
                        "\$${movie.revenue}"
                    }
                    tvDate.text = movie.releaseDate
                    tvTitle.text = movie.title
                    tvSinopsisDesc.text = movie.overview
                }
            })
        }

    }
}