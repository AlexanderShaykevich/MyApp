package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.FragmentNewPostBinding


class NewPostFragment: Fragment() {

    val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)

        binding.edit.requestFocus()

        val intent = Intent()
        binding.edit.setText(intent.getCharSequenceExtra("content"))
        binding.link.setText(intent.getCharSequenceExtra("video"))

        binding.save.setOnClickListener {
            val outIntent = Intent()
            if (binding.edit.text.isNullOrBlank()) {
                activity?.setResult(Activity.RESULT_CANCELED, outIntent)
            } else {
                if(binding.link.text.isNotBlank()) {
                    val url = binding.link.text.toString()
                    outIntent.putExtra("url", url)
                }
                val content = binding.edit.text.toString()
                outIntent.putExtra("content", content)
                activity?.setResult(Activity.RESULT_OK, outIntent)
            }
            activity?.finish()
        }

        binding.cancel.setOnClickListener{
            activity?.finish()
        }


        return binding.root
    }

    class EditPostResult(
        var newContent: String,
        var newVideoUrl: String?,
    )



}

