package ru.netology.nmedia.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.Post

class PostRepositorySharedPrefsImpl(context: Context) : PostRepository {

    private val json = Gson()
    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val key = "posts"
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        prefs.getString(key, null)?.let {
            posts = json.fromJson(it, type)
            data.value = posts
        }
    }

    override fun get(): LiveData<List<Post>> = data

    override fun like(id: Long) {

        posts = posts.map {
            if (it.id != id) {
                it
            } else if (it.id == id && !it.likedByMe) {
                it.copy(counterLike = it.counterLike + 1, likedByMe = !it.likedByMe)
            } else {
                it.copy(counterLike = it.counterLike - 1, likedByMe = !it.likedByMe)
            }
        }
        data.value = posts
        sync()
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(counterShare = it.counterShare + 1)
        }
        data.value = posts
        sync()
    }

    override fun delete(id: Long) {
        posts = posts.filterNot { it.id == id }
        data.value = posts
        sync()
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun insert(post: Post) {
        posts = listOf(post.copy(id = ++nextId)) + posts
        data.value = posts
        sync()
    }

    private fun update(post: Post) {
        posts = posts.map { if (it.id == post.id) post else it }
        data.value = posts
        sync()
    }

    private fun sync() {
        with(prefs.edit()) {
            putString(key, json.toJson(posts))
            apply()
        }
    }

    private companion object {
        const val GENERATED_POST_AMOUNT = 1000
    }

}