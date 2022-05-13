package ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var posts =
        List(10) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "Привет! Это новая Нетология! Попытка номер $index",
                published = "26 апреля 2022"
            )
        }

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data

    override fun like(id: Long) {

            posts = posts.map {
                if (it.id != id) {
                    it
                } else if (it.id == id && !it.likedByMe) {
                    it.copy(counterLike = it.counterLike +1, likedByMe = !it.likedByMe)
                } else {
                    it.copy(counterLike = it.counterLike -1, likedByMe = !it.likedByMe)
                }
            }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(counterShare = it.counterShare +1)
        }
        data.value = posts

    }


}