package ru.netology.nmedia.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.activity.AppActivity.Companion.PostArgs
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import java.io.Serializable


class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)
        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)
        val textPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val key = "saved_text"

        textPrefs.getString(key, null).let {
            binding.edit.setText(it)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val text = binding.edit.text.toString()
            if(text.isNotBlank()) {
                textPrefs.edit().putString(key, text).apply()
            }
            findNavController().navigateUp()
        }

        arguments?.PostArgs?.let {
            binding.edit.setText(it.content)
            binding.link.setText(it.video)
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
            textPrefs.edit().remove(key).apply()
            findNavController().navigateUp()

        }

        binding.cancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

}

class EditPostResult(
    val content: String,
    val video: String? = null,
) : Serializable

