package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.netology.nmedia.data.PostRepositoryInMemoryImpl
import ru.netology.nmedia.data.PostViewModel
import ru.netology.nmedia.databinding.PostBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity(R.layout.post) {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                likesAmount.text = countView(post.counterLike)
                sharesAmount.text = countView(post.counterShare)
                viewsAmount.text = countView(post.counterView)

                usersName.text = post.author
                date.text = post.published
                mainText.text = post.content

                likesImage.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_likes_24dp
                )

                shareImage.setOnClickListener {
                    viewModel.share()
                }

                likesImage.setOnClickListener {
                    viewModel.like()
                }


            }
        }


    }

}








