package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val counterLike: Int = 0,
    val counterShare: Int = 0,
    val counterView: Int = 1,
    val likedByMe: Boolean = false,
    val video: String? = null,
) {

}