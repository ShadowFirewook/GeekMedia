package com.example.geekmedia.presentation.ui.favorites_news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geekmedia.core.extensions.loadImage
import com.example.geekmedia.databinding.ItemFavoriteNewsBinding
import com.example.geekmedia.domain.models.News

class FavoriteNewsAdapter(
    private val onPostClick:(Int) -> Unit,
    private val onDislikeClick:(News.Item) -> Unit,
): ListAdapter<News.Item,FavoriteNewsAdapter.FavoriteNewsViewHolder>(
    FavoritePostDiffUtil()
)  {

    inner class FavoriteNewsViewHolder(
        private val binding: ItemFavoriteNewsBinding
        ): ViewHolder(binding.root){
        fun bind(item: News.Item){
            binding.ivFavoritePost.loadImage(item.image)
            binding.tvNameFavoritePost.text = item.title

            itemView.setOnClickListener{
                onPostClick(item.id)
            }

            binding.ivLiked.setOnClickListener{
                binding.ivLike.isVisible = true
                binding.ivLiked.isVisible = false
                onDislikeClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsViewHolder {
        return FavoriteNewsViewHolder(ItemFavoriteNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class FavoritePostDiffUtil: DiffUtil.ItemCallback<News.Item>(){
        override fun areItemsTheSame(oldItem: News.Item, newItem: News.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News.Item, newItem: News.Item): Boolean {
            return oldItem == newItem
        }
    }

}