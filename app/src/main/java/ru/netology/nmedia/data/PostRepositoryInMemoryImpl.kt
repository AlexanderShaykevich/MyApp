package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.countView


class PostRepositoryInMemoryImpl() : PostRepository {

    private var posts =
        List(10) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "Привет! Это новая Нетология! Попытка номер $index",
                published = "26 апреля 2022"
            )
        }

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun like(postId: Long) {
        for (post in posts) {
            if (post.id == postId) post.counterLike++ else post.counterLike--
        }

        data.value = posts.map {
            if (it.id != postId) it else it.copy(likedByMe = !it.likedByMe)
        }
    }

    override fun share(postId: Long) {
        for (post in posts) {
            if (post.id == postId) post.counterShare++
        }
    }

}