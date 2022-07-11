package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.PostFragment.Companion.KEY_ID
import ru.netology.nmedia.data.PostAdapter
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.util.EditArgs


class FeedFragment : Fragment() {

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

        viewModel.playVideo.observe(viewLifecycleOwner) { videoUrl ->
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
//            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } catch (ex: Exception) {
                Toast.makeText(context, "Wrong video url", Toast.LENGTH_SHORT).show()
            }
//            }
        }

        viewModel.navigateToPostContentScreenEvent.observe(viewLifecycleOwner) { postContentAndVideo ->
            findNavController().navigate(
                R.id.action_feedFragment_to_newPostFragment,
                Bundle().apply {
                    EditPostArgs =
                        EditPostResult(postContentAndVideo.content, postContentAndVideo?.video)

                })
        }

        viewModel.openPostEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_feedFragment_to_postFragment,
                Bundle().apply {
                    putLong(KEY_ID, it)
                }
            )
        }


        return binding.root
    }

    companion object {
        var Bundle.EditPostArgs: EditPostResult by EditArgs
    }


}












