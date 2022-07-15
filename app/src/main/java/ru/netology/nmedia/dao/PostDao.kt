package ru.netology.nmedia.dao

import ru.netology.nmedia.dto.Post

interface PostDao {
    fun get(): List<Post>
    fun save(post: Post): Post
    fun like(id: Long)
    fun delete(id: Long)
    fun share(id: Long)
}