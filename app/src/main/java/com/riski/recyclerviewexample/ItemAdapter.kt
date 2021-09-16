package com.riski.recyclerviewexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riski.recyclerviewexample.data.MovieData
import com.riski.recyclerviewexample.databinding.ListItemBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    var mItems = ArrayList<MovieData>()

    fun setItems(items: ArrayList<MovieData>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(item: MovieData) {
            Glide.with(itemView)
                .load(item.poster)
                .into(binding.imgPoster)

            binding.title.text = item.title
            binding.date.text = "Release: ${item.date}"
            binding.sinopsis.text = item.sinopsis

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, item)
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