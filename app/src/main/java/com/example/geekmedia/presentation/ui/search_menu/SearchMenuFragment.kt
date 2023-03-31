package com.example.geekmedia.presentation.ui.search_menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekmedia.R
import com.example.geekmedia.core.*
import com.example.geekmedia.databinding.FragmentSearchBinding
import com.example.geekmedia.domain.models.News

import com.example.geekmedia.presentation.ui.base.BaseFragment
import com.example.geekmedia.presentation.ui.home.HomeViewModel
import com.example.geekmedia.presentation.ui.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMenuFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    private val newsList = arrayListOf<News.Item>()
    private val filteredList = arrayListOf<News.Item>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter(
            filteredList,
            this::onPostClick,
            this::onLikeClick,
            this::onShareClick
        )
    }

    override fun initAdapter() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun initClick() {
        onSearch()
        openSettings()
        onEventsClick()
        onProjectManagementClick()
        onMobileDevelopmentClick()
        onWebDevelopmentClick()
        onFrontendClick()
        onBackendClick()
        onBackButtonPressed()
    }

    private fun onSearch() {
        binding.searchNews.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                for (item in newsList){
                    if (item.title.contains(query.toString())){
                        filteredList.add(item)
                    }
                }
                binding.containerCategories.isVisible = false
                binding.rvNews.isVisible = true
                binding.btnBack.isVisible = true
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun initRequests() {
        viewModel.getNews()
    }

    override fun notifySubscribers() {
        viewModel.getNewsState.collectState(
            onLoading = {
                binding.progressBar.isVisible = true
                binding.rvNews.isVisible = false
            },
            onError = {
                //Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            },
            onSuccess = { news ->
                binding.progressBar.isVisible = false
                binding.rvNews.isVisible = true

                newsList.clear()
                newsList.addAll(news.results)
            }
        )
    }

    private fun onBackButtonPressed(){
        binding.btnBack.setOnClickListener {
            binding.rvNews.isVisible = false
            binding.containerCategories.isVisible = true
            binding.btnBack.isVisible = false
        }
    }

    private fun openSettings(){
        binding.ivSettings.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_menu_to_settingsFragment)
        }
    }

    private fun onEventsClick(){
        binding.eventsCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Events")
            )
        }
    }

    private fun onProjectManagementClick() {
        binding.projectManagementCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Project Management")
            )
        }
    }

    private fun onMobileDevelopmentClick() {
        binding.mobileDevelopmentCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Mobile Development")
            )
        }
    }

    private fun onWebDevelopmentClick() {
        binding.webDevelopmentCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Web-Development")
            )
        }
    }

    private fun onFrontendClick() {
        binding.frontendCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Frontend")
            )
        }
    }

    private fun onBackendClick() {
        binding.backendCategory.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_search_menu_to_filteredNewsFragment,
                bundleOf(CATEGORY to "Backend")
            )
        }
    }

    private fun onPostClick(id: Int) {
        findNavController().navigate(
            R.id.action_navigation_search_menu_to_postFragment,
            bundleOf(POST_ID to id)
        )
        filteredList.clear()
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