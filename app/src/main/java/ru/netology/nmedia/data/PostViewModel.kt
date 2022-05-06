package ru.netology.nmedia.data

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)

}