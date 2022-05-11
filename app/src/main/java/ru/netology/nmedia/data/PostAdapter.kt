package ru.netology.nmedia.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.countView
import ru.netology.nmedia.databinding.PostBinding

typealias OnLikeShareListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeShareListener,
    private val onShareListener: OnLikeShareListener,
) : ListAdapter<Post, ViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class ViewHolder(
    private val binding: PostBinding,
    private val onLikeListener: OnLikeShareListener,
    private val onShareListener: OnLikeShareListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            likesAmount.text = countView(post.counterLike)
            sharesAmount.text = countView(post.counterShare)
            viewsAmount.text = countView(post.counterView)

            usersName.text = post.author
            date.text = post.published
            mainText.text = post.content

            likesImage.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24dp else R.drawable.ic_likes_24dp
            )

            shareImage.setOnClickListener {
                onShareListener(post)
            }

            likesImage.setOnClickListener {
                onLikeListener(post)
            }
        }

    }

}

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
