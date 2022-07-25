package ru.netology.nmedia.service


import com.google.gson.annotations.SerializedName

class NewPost(
    @SerializedName("userId")
    val userId: Long,

    @SerializedName("postId")
    val postId: Long,

    @SerializedName("postAuthor")
    val postAuthor: String,

    @SerializedName("postContent")
    val postContent: String
)