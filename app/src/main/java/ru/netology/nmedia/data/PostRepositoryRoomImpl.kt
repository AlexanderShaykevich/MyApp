package ru.netology.nmedia.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.toEntity
import ru.netology.nmedia.entity.toModel

class PostRepositoryRoomImpl(
    context: Context,
    private val dao: PostDao,
) : PostRepository {
    private var nextId = 0L
    private val idPref = context.getSharedPreferences("id", Context.MODE_PRIVATE)
    private val key = "id_key"

    init {
        nextId = idPref.getLong(key, 0)
    }

    override fun get(): LiveData<List<Post>> = dao.get().map { entities ->
        entities.map { it.toModel() }
    }

    override fun like(id: Long) {
        dao.like(id)
    }

    override fun share(id: Long) {
        dao.share(id)
    }

    override fun delete(id: Long) {
        dao.delete(id)
    }

    override fun save(post: Post) {
        dao.save(post.toEntity())
        saveId()
    }

    private fun saveId() {
        idPref.edit().apply {
            putLong(key, nextId)
            apply()
        }
    }


}