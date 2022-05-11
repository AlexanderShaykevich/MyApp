package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.netology.nmedia.data.PostAdapter
import ru.netology.nmedia.data.PostRepositoryInMemoryImpl
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity(R.layout.post) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostAdapter {
            viewModel.like(it.id)
            viewModel.share(it.id)
        }
        binding.list.adapter = adapter



        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
            }


        }

    }










