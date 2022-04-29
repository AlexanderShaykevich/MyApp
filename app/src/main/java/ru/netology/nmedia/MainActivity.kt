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
                likesAmount.text = countView(viewModel.getCountLikes())
                sharesAmount.text = countView(viewModel.getCountShares())
                viewsAmount.text = countView(viewModel.getCountViews())

                usersName.text = post.author
                date.text = post.published
                mainText.text = post.content

                likesImage.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_likes_24dp
                )

                shareImage.setOnClickListener {
                    viewModel.share()
                    sharesAmount.text = countView(viewModel.getCountShares())
                }

                likesImage.setOnClickListener {
                    viewModel.like()
                    likesAmount.text = countView(viewModel.getCountLikes())
                }


            }
        }


    }

}








