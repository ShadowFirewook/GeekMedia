package com.example.geekmedia.presentation.ui.post

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.geekmedia.core.POST_ID
import com.example.geekmedia.core.extensions.loadImage
import com.example.geekmedia.data.mappers.toNewsItem
import com.example.geekmedia.databinding.FragmentPostBinding
import com.example.geekmedia.domain.models.News
import com.example.geekmedia.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>() {

    private val viewModel by viewModels<PostViewModel>()
    private var postId:Int? = null
    private var post:News.Item? = null

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentPostBinding {
        return FragmentPostBinding.inflate(layoutInflater)
    }

    override fun getData() {
        postId = arguments?.getInt(POST_ID)
    }

    override fun initRequests() {
        postId?.let { viewModel.getPost(it) }
        binding.ivLike.setOnClickListener {
            binding.ivLiked.isVisible = true
            binding.ivLike.isVisible = false
            post?.let { it1 -> viewModel.createFavoritePost(it1) }
        }
    }

    override fun notifySubscribers() {
        viewModel.getPostState.collectState(
            onLoading = {
                binding.progressBar.isVisible = true
                binding.scrollView.isVisible = false
            },
            onError = {
                //Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                binding.scrollView.isVisible = true
                binding.progressBar.isVisible = false
                binding.tvPostName.text = it.title
                binding.tvPostDescription.text = it.description
                binding.tvCategory.text = it.category.ru_title
                binding.tvCreatedDateTime.text = it.created_date_time
                binding.ivPostImage.loadImage(it.image)
                post = it.toNewsItem()
            }

        )
    }

    override fun initView() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}