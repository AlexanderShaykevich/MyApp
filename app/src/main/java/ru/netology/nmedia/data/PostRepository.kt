package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import ru.netology.nmedia.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    var likesCount: Int
    var shareCount: Int
    var viewCount: Int


}
