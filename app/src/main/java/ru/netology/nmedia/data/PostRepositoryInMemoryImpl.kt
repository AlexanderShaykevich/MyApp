package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.countView


class PostRepositoryInMemoryImpl() : PostRepository {

    private var post = Post(
        id = 1L,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет! Это новая Нетология!",
        published = "26 апреля 2022"
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        if (!post.likedByMe) post.counterLike++ else post.counterLike--
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }

    override fun share() {
        post.counterShare++
    }

}