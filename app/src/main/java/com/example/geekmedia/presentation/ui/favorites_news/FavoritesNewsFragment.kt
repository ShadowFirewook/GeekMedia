package com.example.geekmedia.presentation.ui.favorites_news

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekmedia.R
import com.example.geekmedia.core.POST_ID
import com.example.geekmedia.databinding.FragmentFavoritesNewsBinding
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesNewsFragment: BaseFragment<FragmentFavoritesNewsBinding>() {

    private val viewModel by viewModels<FavoritesNewsViewModel>()
    private lateinit var favoriteNewsAdapter: FavoriteNewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteNewsAdapter = FavoriteNewsAdapter(
            this::onPostClick,this::onDislikeClick)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentFavoritesNewsBinding {
        return FragmentFavoritesNewsBinding.inflate(layoutInflater)
    }

        override fun initAdapter() {
            binding.rvLikedNews.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = favoriteNewsAdapter
            }
        }

    override fun initRequests() {
        viewModel.getFavoritesNews()
        refreshFavoritesNews()
    }

    override fun notifySubscribers() {
        viewModel.getFavoritesNewsState.collectState(
            onLoading ={

            },
            onError ={

            },
            onSuccess = {
                favoriteNewsAdapter.submitList(it)
                refreshFavoritesNews()

            }
        )
        viewModel.deleteFavoritePostState.collectState(
            onLoading = {

            },
            onError = {

            },
            onSuccess = {
                viewModel.getFavoritesNews()
                refreshFavoritesNews()
            }
        )
    }

    private fun onDislikeClick(item: News.Item) {
        viewModel.deleteFavoritePost(item)
    }

    private fun onPostClick(id: Int) {
        findNavController().navigate(
            R.id.action_navigation_favorites_news_to_postFragment,
            bundleOf(POST_ID to id)
        )
    }

    private fun refreshFavoritesNews(){
        if (favoriteNewsAdapter.currentList.size == 0){
            binding.rvLikedNews.isVisible = false
            binding.swipeRefreshData.isVisible = false
            binding.ivFavorites.isVisible = true
            binding.tvNoNews.isVisible = true
        } else {
            binding.rvLikedNews.isVisible = true
            binding.swipeRefreshData.isVisible = true
            binding.swipeRefreshData.setOnRefreshListener {
                binding.swipeRefreshData.isRefreshing = false
                favoriteNewsAdapter.notifyItemChanged(favoriteNewsAdapter.currentList.lastIndex)
            }
        }
    }

}