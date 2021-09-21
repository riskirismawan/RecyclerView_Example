package com.riski.recyclerviewexample.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riski.recyclerviewexample.R
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import com.riski.recyclerviewexample.databinding.ListItemBinding
import com.riski.recyclerviewexample.ui.detail.DetailActivity

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    var mItems = ArrayList<MoviesItem>()

    fun setItems(items: List<MoviesItem>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(item: MoviesItem) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/original${item.posterPath}")
                .into(binding.imgPoster)

            binding.title.text = item.title
            binding.date.text = "Release: ${item.releaseDate}"
            binding.sinopsis.text = item.overview

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, item.id)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int = mItems.size
}