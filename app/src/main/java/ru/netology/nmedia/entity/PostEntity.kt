package ru.netology.nmedia.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity(tableName = "posts")
class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "published")
    val published: String,
    @ColumnInfo(name = "counterLike")
    val counterLike: Int = 0,
    @ColumnInfo(name = "counterShare")
    val counterShare: Int = 0,
    @ColumnInfo(name = "counterView")
    val counterView: Int = 1,
    @ColumnInfo(name = "likedByMe")
    val likedByMe: Boolean,
    @ColumnInfo(name = "video")
    val video: String?,
)

internal fun PostEntity.toModel() = Post(
    id = id,
    author = author,
    content = content,
    published = published,
    counterLike = counterLike,
    counterShare = counterShare,
    counterView = counterView,
    likedByMe = likedByMe,
    video = video,
)

internal fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    content = content,
    published = published,
    counterLike = counterLike,
    counterShare = counterShare,
    counterView = counterView,
    likedByMe = likedByMe,
    video = video,
)