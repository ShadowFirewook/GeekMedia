package com.example.geekmedia.presentation.ui.search_menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekmedia.R
import com.example.geekmedia.core.CATEGORY
import com.example.geekmedia.core.POST_ID
import com.example.geekmedia.databinding.FragmentFilteredNewsBinding
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.presentation.ui.base.BaseFragment
import com.example.geekmedia.presentation.ui.home.HomeViewModel
import com.example.geekmedia.presentation.ui.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilteredNewsFragment : BaseFragment<FragmentFilteredNewsBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private val newsList = arrayListOf<News.Item>()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var category:String

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentFilteredNewsBinding {
        return FragmentFilteredNewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter(
            newsList,
            this::onPostClick,
            this::onLikeClick,
            this::onShareClick
        )
    }

    override fun getData() {
        category = requireArguments().getString(CATEGORY)!!
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

            },
            onSuccess = { news ->
                binding.progressBar.isVisible = false
                binding.swipeRefreshData.isVisible = true
                binding.rvNews.isVisible = true

                newsList.clear()
                filterNews(news)
                newsAdapter.notifyItemChanged(newsList.lastIndex)
            }
        )
    }

    override fun initView() {
        binding.tvCategory.text = category
        updateNews()
        onBackButtonPressed()
    }

    private fun filterNews(news: News){
        when(category){
            "Events" ->   newsList.addAll(news.results.filter { it.category == "Events" })
            "Project Management" ->   newsList.addAll(news.results.filter { it.category == "Project Management" })
            "Mobile Development" ->   newsList.addAll(news.results.filter { it.category == "Mobile Development" })
            "Web-Development" ->   newsList.addAll(news.results.filter { it.category == "Web-Development" })
            "Frontend" ->   newsList.addAll(news.results.filter { it.category == "Frontend" })
            "Backend" ->   newsList.addAll(news.results.filter { it.category == "Backend" })
        }
    }

    private fun onBackButtonPressed(){
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateNews() {
        binding.swipeRefreshData.setOnRefreshListener {
            binding.swipeRefreshData.isRefreshing = false
        }
    }

    private fun onPostClick(id: Int) {
        findNavController().navigate(
            R.id.action_filteredNewsFragment_to_postFragment,
            bundleOf(POST_ID to id)
        )
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

    private fun onShareClick(link:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}