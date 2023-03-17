package com.example.geekmedia.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekmedia.R
import com.example.geekmedia.core.POST_ID
import com.example.geekmedia.databinding.FragmentHomeBinding
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private val newsList = arrayListOf<News.Item>()
    private lateinit var newsAdapter:NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter(
            newsList,
            this::onPostClick,
            this::onLikeClick,
            this::onDislikeClick
        )
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initAdapter() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun initRequests() {
        viewModel.getNews()
    }

    override fun notifySubscribers() {
                viewModel.getNewsState.collectState(
                    onLoading = {
                        binding.progressBar.isVisible = true
                        binding.swipeRefreshData.isVisible = false
                        binding.rvNews.isVisible = false
                    },
                    onError = {
                        //Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    },
                    onSuccess = {
                        binding.progressBar.isVisible = false
                        binding.swipeRefreshData.isVisible = true
                        binding.rvNews.isVisible = true

                        newsList.clear()
                        newsList.addAll(it.results)
                        newsAdapter.notifyItemChanged(newsList.lastIndex)
                    }
                )
        }


    override fun initView() {
        binding.swipeRefreshData.setOnRefreshListener {
            binding.swipeRefreshData.isRefreshing = false
            newsAdapter.notifyItemChanged(newsList.lastIndex)
        }
    }

    private fun onPostClick(id: Int) {
        findNavController().navigate(
            R.id.action_navigation_home_to_postFragment,
            bundleOf(POST_ID to id))
    }

    private fun onLikeClick(item: News.Item) {
        viewModel.createFavoritePost(News.Item(
            category = item.category,
            created_date = item.created_date,
            created_date_time = item.created_date_time,
            description = item.description,
            image = item.image,
            title = item.title,
            id = item.id
        ))
    }

    private fun onDislikeClick(item: News.Item) {
        viewModel.deleteFavoritePost(item)
    }

}