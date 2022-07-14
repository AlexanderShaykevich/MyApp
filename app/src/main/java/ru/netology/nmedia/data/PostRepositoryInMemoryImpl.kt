package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var nextId = GENERATED_POST_AMOUNT.toLong()

    private var posts =
        List(GENERATED_POST_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "Привет! Это новая Нетология! Попытка номер $index",
                published = "26 апреля 2022"
            )
        }

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun like(id: Long) {

        posts = posts.map {
            if (it.id != id) {
                it
            } else if (it.id == id && !it.likedByMe) {
                it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
            } else {
                it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
            }
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(counterShare = it.counterShare + 1)
        }
        data.value = posts
    }

    override fun delete(id: Long) {
        posts = posts.filterNot { it.id == id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        posts = listOf(post.copy(id = ++nextId)) + posts
        data.value = posts
    }

    private fun update(post: Post) {
        posts = posts.map { if (it.id == post.id) post else it }
        data.value = posts

    }

    private companion object {
        const val GENERATED_POST_AMOUNT = 1000
    }

}