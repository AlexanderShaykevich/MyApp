package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.activity.FeedFragment.Companion.EditPostArgs
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import java.io.Serializable


class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)

//        arguments?.apply {
//            StringArg?.let { binding.edit.setText(it) }
//            EditArgs.let {
//                binding.edit.setText(it.content)
//                binding.link.setText(it.video)
//            }
//        }

        if (!arguments?.textArg.isNullOrBlank()) {
            arguments?.textArg?.let(binding.edit::setText)
        } else {
            arguments?.EditPostArgs?.let {
                binding.edit.setText(it.content)
                binding.link.setText(it.video)
            }
        }

        binding.edit.requestFocus()

        binding.save.setOnClickListener {
            if (!binding.edit.text.isNullOrBlank()) {
                val content = binding.edit.text.toString()
                var url: String? = null

                if (binding.link.text.isNotBlank()) {
                    url = binding.link.text.toString()
                }
                viewModel.onSaveButtonListener(content, url)
            }
            findNavController().navigateUp()

        }

        binding.cancel.setOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }

    companion object {
        var Bundle.textArg: String? by StringArg
    }


}

class EditPostResult(
    val content: String,
    val video: String?
) : Serializable

