package ru.netology.nmedia.data

import ru.netology.nmedia.dto.Post

interface PostInteractionListener {
    fun onLikeListener(post: Post)
    fun onShareListener(post: Post)
    fun onDeleteListener(post: Post)
    fun onEditListener(post: Post)
    fun onVideoPlayClickListener(post: Post)
    fun onPostClickListener(post: Post)
}