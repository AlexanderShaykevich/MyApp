package ru.netology.nmedia.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.netology.nmedia.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun get(): LiveData<List<PostEntity>>

    @Insert
    fun insert(post: PostEntity)

    @Query("""
        UPDATE posts SET 
        content = :content, video = :video
        WHERE id = :id
        """)
    fun updateContentById(id: Long, content: String, video: String?)

    fun save(post: PostEntity) =
        if (post.id == 0L) insert(post) else updateContentById(post.id, post.content, post.video)

    @Query("""
        UPDATE posts SET
        counterLike = counterLike + CASE WHEN likedByMe THEN -1 ELSE 1 END,
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE id = :id
        """)
    fun like(id: Long)

    @Query("""
        UPDATE posts SET
        counterShare = counterShare + 1 WHERE id = :id
        """)
    fun share(id: Long)

    @Query("DELETE FROM posts WHERE id = :id")
    fun delete(id: Long)
}