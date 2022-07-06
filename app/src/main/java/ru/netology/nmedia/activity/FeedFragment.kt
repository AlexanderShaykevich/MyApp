package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.data.PostAdapter
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.FragmentFeedBinding


class FeedFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)
        val adapter = PostAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }

        viewModel.sharePostContent.observe(viewLifecycleOwner) { postContent ->
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, postContent)
                type = "text/plain"
            }
            val shareIntent =
                Intent.createChooser(intent, getString(R.string.chooser_share_post))
            startActivity(shareIntent)
        }

//        viewModel.playVideo.observe(viewLifecycleOwner) { videoUrl ->
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }
//        }
//
//        val postContentActivityLauncher =
//            registerForActivityResult(ResultContract) { postContentAndVideo ->
//                postContentAndVideo ?: return@registerForActivityResult
//                viewModel.onSaveButtonListener(
//                    postContentAndVideo.newContent,
//                    postContentAndVideo.newVideoUrl
//                )
//            }

//        viewModel.navigateToPostContentScreenEvent.observe(viewLifecycleOwner) { postContentAndVideo ->
//            postContentActivityLauncher.launch(postContentAndVideo)
//        }


        return binding.root
    }

//    companion object {
//private const val TEXT_KEY = "TEXT_KEY"
//        var Bundle.textArg: String?
//        set(value) = putString(TEXT_KEY, value)
//        get() = getString(TEXT_KEY)
//    }

}












