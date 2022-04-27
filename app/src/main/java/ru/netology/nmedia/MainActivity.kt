package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.PostBinding
import kotlin.math.floor

class MainActivity : AppCompatActivity(R.layout.post) {
    var likesCount = 999
    var shareCount = 9998
    var viewCount = 500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PostBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val post = Post(
            id = 1L,
            author = "Нетология. Университет интернет-профессий",
            content = "Привет! Это новая Нетология!",
            published = "26.04.2022"
        )

        with(binding) {
            likesAmount.text = countView(likesCount)
            sharesAmount.text = countView(shareCount)
            viewsAmount.text = countView(viewCount)
            
            usersName.text = post.author
            date.text = post.published
            mainText.text = post.content

            if (post.likedByMe) {
                likesImage.setImageResource(R.drawable.ic_liked_24dp)
        }

            likesImage.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likesImage.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_likes_24dp
                )

                if (post.likedByMe) {
                    likesCount++
                } else {
                    likesCount--
                }
                likesAmount.text = countView(likesCount)
            }

            shareImage.setOnClickListener {
                shareCount++
                sharesAmount.text = countView(shareCount)
            }

        }

    }



}




