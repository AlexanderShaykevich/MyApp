package ru.netology.nmedia.data

import androidx.lifecycle.ViewModel

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()

    fun getCountLikes() = repository.likesCount
    fun getCountShares() = repository.shareCount
    fun getCountViews() = repository.viewCount

}