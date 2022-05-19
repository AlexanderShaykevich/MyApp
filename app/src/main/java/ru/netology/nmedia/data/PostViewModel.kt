package ru.netology.nmedia.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.databinding.ActivityMainBinding

class PostViewModel : ViewModel(), PostInteractionListener {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveButtonListener(content: String) {
        val post = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Alexander",
            content = content,
            published = "18 мая 2022"
        )
        repository.save(post)
        currentPost.value = null
    }

    override fun onLikeListener(post: Post) = repository.like(post.id)
    override fun onShareListener(post: Post) = repository.share(post.id)
    override fun onDeleteListener(post: Post) = repository.delete(post.id)
    override fun onEditListener(post: Post) {
        currentPost.value = post
    }


}