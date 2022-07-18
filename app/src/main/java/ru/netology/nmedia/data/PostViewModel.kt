package ru.netology.nmedia.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.activity.EditPostResult
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.util.SingleLiveEvent

class PostViewModel(application: Application) : AndroidViewModel(application),
    PostInteractionListener {
    private val repository: PostRepository = PostRepositoryRoomImpl(
        application,
        AppDb.getInstance(application).postDao
    )
    val data = repository.get()
    private val currentPost = MutableLiveData<Post?>(null)
    val sharePostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<EditPostResult>()
    val playVideo = SingleLiveEvent<String>()
    val openPostEvent = SingleLiveEvent<Long>()

    fun onSaveButtonListener(content: String, url: String?) {
        val post = currentPost.value?.copy(
            content = content,
            video = url
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Alexander",
            content = content,
            published = "18 мая 2022",
            video = url
        )
        repository.save(post)
        currentPost.value = null
    }


    override fun onLikeListener(post: Post) = repository.like(post.id)
    override fun onShareListener(post: Post) {
        repository.share(post.id)
        sharePostContent.value = post.content
    }

    override fun onDeleteListener(post: Post) = repository.delete(post.id)

    override fun onEditListener(post: Post) {
        currentPost.value = post
        navigateToPostContentScreenEvent.value = EditPostResult(post.content, post.video)
    }

    override fun onVideoPlayClickListener(post: Post) {
        val url = requireNotNull(post.video)
        playVideo.value = url
    }

    override fun onPostClickListener(post: Post) {
        openPostEvent.value = post.id
    }



}