package com.example.geekmedia.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geekmedia.core.extensions.loadImage
import com.example.geekmedia.databinding.ItemNewsBinding
import com.example.geekmedia.domain.models.News

class NewsAdapter(
    private val newsList: ArrayList<News.Item>,
    private val onPostClick:(Int) -> Unit,
    private val onLikeClick: (News.Item) -> Unit,
    private val onDislikeClick: (News.Item) -> Unit,
) : Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = newsList.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(newsList[position])
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding):ViewHolder(binding.root) {
        fun bind(item: News.Item) {
            binding.ivPost.loadImage(item.image)
            binding.tvPost.text = item.title
            binding.tvCategory.text = item.category
            binding.tvCreatedDate.text = item.created_date_time

            itemView.setOnClickListener{
                onPostClick(item.id)
            }

            binding.ivLike.setOnClickListener {
                onLikeClick(item)
                binding.ivLiked.isVisible = true
                binding.ivLike.isVisible = false
            }

            binding.ivLiked.setOnClickListener {
                onDislikeClick(item)
                binding.ivLike.isVisible = true
                binding.ivLiked.isVisible = false
            }
        }
    }

}



