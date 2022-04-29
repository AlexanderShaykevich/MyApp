package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.countView


class PostRepositoryInMemoryImpl() : PostRepository {

    override var likesCount = 999999
    override var shareCount = 0
    override var viewCount = 500

    private var post = Post(
        id = 1L,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет! Это новая Нетология!",
        published = "26 апреля 2022"
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post

        if (post.likedByMe) {
            likesCount++
        } else {
            likesCount--
        }

    }

    override fun share() {
        shareCount++
    }

}