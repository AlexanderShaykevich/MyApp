package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import ru.netology.nmedia.Post

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun like(id: Long)
    fun share(id: Long)
    fun delete(id: Long)
    fun save(post: Post)

    companion object {
        const val NEW_POST_ID = 0L
    }
}