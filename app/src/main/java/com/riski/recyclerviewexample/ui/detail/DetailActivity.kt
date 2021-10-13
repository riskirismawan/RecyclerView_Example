package com.riski.recyclerviewexample.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import com.riski.recyclerviewexample.databinding.ActivityDetailBinding
import com.riski.recyclerviewexample.presenter.MainPresenter
import com.riski.recyclerviewexample.presenter.MainView
import com.riski.recyclerviewexample.viewmodel.MovieViewModel

class DetailActivity : AppCompatActivity(), MainView {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenter()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)

        val movieid = intent.extras?.getInt(DATA)

        if (movieid != null) {
            presenter.getDataById(movieid)
//            viewModel.getMovieDetail(movieid).observe(this, { movie ->
//                binding.apply {
//                    Glide.with(this@DetailActivity)
//                        .load("https://image.tmdb.org/t/p/original${movie.posterPath}")
//                        .into(imgPhoto)
//
//                    tvUserScoreValue.text = movie.voteAverage.toString()
//                    tvBudgetValue.text = if (movie.revenue == 0) {
//                        "-"
//                    } else {
//                        "\$${movie.budget}"
//                    }
//                    tvRevenueValue.text = if (movie.revenue == 0) {
//                        "-"
//                    } else {
//                        "\$${movie.revenue}"
//                    }
//                    tvDate.text = movie.releaseDate
//                    tvTitle.text = movie.title
//                    tvSinopsisDesc.text = movie.overview
//                }
//            })
        }

    }

    override fun onSuccess(data: List<MoviesItem>?) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${data?.get(0)?.posterPath}")
                .into(imgPhoto)

            tvUserScoreValue.text = data?.get(0)?.voteAverage.toString()
            tvBudgetValue.text = if (data?.get(0)?.revenue == 0) {
                "-"
            } else {
                "\$${data?.get(0)?.budget}"
            }
            tvRevenueValue.text = if (data?.get(0)?.revenue == 0) {
                "-"
            } else {
                "\$${data?.get(0)?.revenue}"
            }
            tvDate.text = data?.get(0)?.releaseDate
            tvTitle.text = data?.get(0)?.title
            tvSinopsisDesc.text = data?.get(0)?.overview
        }
    }

    override fun onFailure() {
        Toast.makeText(this, "Gagal Mengambil data", Toast.LENGTH_SHORT).show()
        Log.e("DetailActivity", "onFailure: Failed get data")
    }

    override fun onAttachView() {
        presenter.onAttach(this)
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