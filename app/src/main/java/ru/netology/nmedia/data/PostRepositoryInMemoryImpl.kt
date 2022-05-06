package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var post = Post(
        id = 1L,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет! Это новая Нетология!",
        published = "26 апреля 2022"
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
      post = if (!post.likedByMe) {
            post.copy(counterLike = post.counterLike + 1, likedByMe = !post.likedByMe)
        } else {
            post.copy(counterLike = post.counterLike - 1, likedByMe = !post.likedByMe)
        }
        data.value = post
    }

    override fun share() {
        post = post.copy(counterShare = post.counterShare + 1)
        data.value = post

    }

}