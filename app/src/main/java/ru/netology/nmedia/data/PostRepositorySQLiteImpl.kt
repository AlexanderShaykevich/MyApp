package ru.netology.nmedia.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post

class PostRepositorySQLiteImpl(
    private val context: Context,
    private val dao: PostDao
    ) : PostRepository {
    private var nextId = 0L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)
    private val idPref = context.getSharedPreferences("id", Context.MODE_PRIVATE)
    private val key = "id_key"

    init {
        nextId = idPref.getLong(key, 0)
        posts = dao.get()
        data.value = posts
    }

    override fun get(): LiveData<List<Post>> = data

    override fun like(id: Long) {
        dao.like(id)
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
        dao.share(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(counterShare = it.counterShare + 1)
        }
        data.value = posts
    }

    override fun delete(id: Long) {
        dao.delete(id)
        posts = posts.filterNot { it.id == id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        val saved = dao.save(post)
        posts = listOf(saved.copy(id = ++nextId)) + posts
        data.value = posts
        saveId()
    }

    private fun update(post: Post) {
        dao.save(post)
        posts = posts.map { if (it.id == post.id) post else it }
        data.value = posts
    }

    private fun saveId() {
        idPref.edit().apply {
            putLong(key, nextId)
            apply()
        }
    }


}