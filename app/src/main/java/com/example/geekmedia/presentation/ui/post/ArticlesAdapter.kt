package com.example.geekmedia.presentation.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geekmedia.core.extensions.loadImage
import com.example.geekmedia.databinding.ItemArticleBinding
import com.example.geekmedia.domain.models.Post

class ArticlesAdapter(
    private var articlesList: ArrayList<Post.Article>
) : Adapter<ArticlesAdapter.ArticlesViewHolder>() {



    inner class ArticlesViewHolder(private val binding: ItemArticleBinding): ViewHolder(binding.root){

        fun bind(article: Post.Article){
            binding.tvArticleTitle.text = article.title
            binding.tvArticleDescription.text = article.description
            if (article.image != null){
                binding.ivArticle.loadImage(article.image)
            } else{
                binding.ivArticle.isVisible = false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return  ArticlesViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = articlesList.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(articlesList[position])
    }


}