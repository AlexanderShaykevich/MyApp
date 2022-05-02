package ru.netology.nmedia

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var counterLike:Int = 999,
    var counterShare:Int = 0,
    var counterView:Int = 1200,
    var likedByMe: Boolean = false,
        ) {

}