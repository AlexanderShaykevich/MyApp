package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.data.PostAdapter
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding

import ru.netology.nmedia.util.hideKeyboard

class MainActivity : AppCompatActivity(R.layout.post) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.saveButton.setOnClickListener {
            with(binding.textWindow) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Text can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val content = text.toString()
                viewModel.onSaveButtonListener(content)
                clearFocus()
                hideKeyboard()
                binding.editGroup.visibility = View.GONE
            }
        }

        binding.editCancelButton.setOnClickListener {
            viewModel.currentPost.value = null
            binding.editGroup.visibility = View.GONE
        }

        viewModel.currentPost.observe(this) { currentPost ->
            with(binding.textWindow) {
                val content = currentPost?.content
                setText(content)
                if (content != null) {
                    requestFocus()
                    binding.editGroup.visibility = View.VISIBLE
                    binding.editTextBottom.text = content
                }
            }

        }

    }


}










