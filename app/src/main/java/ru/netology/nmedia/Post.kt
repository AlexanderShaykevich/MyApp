package ru.netology.nmedia

class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var counterLike:Int = 0,
    var counterShare:Int = 0,
    var counterView:Int = 0,
    var likedByMe: Boolean = false,
        ) {

}