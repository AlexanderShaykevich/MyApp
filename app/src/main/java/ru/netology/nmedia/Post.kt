package ru.netology.nmedia

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val counterLike:Int = 999,
    val counterShare:Int = 0,
    val counterView:Int = 1200,
    val likedByMe: Boolean = false,
        ) {

}