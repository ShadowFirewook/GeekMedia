package com.example.geekmedia.presentation.ui.home

import android.content.Intent
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
            this::onShareClick
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
                    onSuccess = { news ->
                        binding.progressBar.isVisible = false
                        binding.swipeRefreshData.isVisible = true
                        binding.rvNews.isVisible = true

                        newsList.clear()
                        newsList.addAll(news.results.sortedByDescending { it.created_date_time })
                        newsAdapter.notifyItemChanged(newsList.lastIndex)
                    }
                )
        }

    override fun initView() {
        openSettings()
        updateNews()
    }

    private fun updateNews() {
        binding.swipeRefreshData.setOnRefreshListener {
            binding.swipeRefreshData.isRefreshing = false
        }
    }

    private fun openSettings(){
        binding.ivSettings.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_settingsFragment)
        }
    }

    private fun onPostClick(id: Int) {
        findNavController().navigate(
            R.id.action_navigation_home_to_postFragment,
            bundleOf(POST_ID to id))
    }

    private fun onShareClick(link:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
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

}