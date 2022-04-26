package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.PostBinding

class MainActivity : AppCompatActivity(R.layout.post) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = PostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1L,
            author = "Alexander",
            content = "Hello! It is a project by Netology",
            published = "26.04.2022"
        )

        with(binding) {
            usersName.text = post.author
            date.text = post.published
            mainText.text = post.content
            if(post.likedByMe) {
                likesImage.setImageResource(R.drawable.ic_liked_24dp)

                likesImage.setOnClickListener {
                    post.likedByMe = !post.likedByMe
                    likesImage.setImageResource (
                        if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_likes_24dp
                    )

                }
        }



    }
}