package com.riski.recyclerviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.riski.recyclerviewexample.data.MovieData
import com.riski.recyclerviewexample.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.get(DATA) as MovieData

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(movie.poster)
                .into(imgPhoto)

            tvUserScoreValue.text = movie.userScore.toString()
            tvBudgetValue.text = if (movie.revenue == null) {
                "-"
            } else {
                "\$${movie.budget}"
            }
            tvRevenueValue.text = if (movie.revenue == null) {
                "-"
            } else {
                "\$${movie.revenue}"
            }
            tvDate.text = movie.date
            tvTitle.text = movie.title
            tvSinopsisDesc.text = movie.sinopsis
        }
    }
}