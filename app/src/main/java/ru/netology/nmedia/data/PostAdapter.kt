package ru.netology.nmedia.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.countView
import ru.netology.nmedia.databinding.PostBinding

class PostAdapter(
    private val interactionListener: PostInteractionListener
) : ListAdapter<Post, ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class ViewHolder(
    private val binding: PostBinding,
    private val listener: PostInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            shareImage.text = countView(post.counterShare)
            viewsAmount.text = countView(post.counterView)

            usersName.text = post.author
            date.text = post.published
            mainText.text = post.content
            likesImage.text = post.counterLike.toString()
            likesImage.isChecked = post.likedByMe

            shareImage.setOnClickListener {
                listener.onShareListener(post)
            }

            likesImage.setOnClickListener {
                listener.onLikeListener(post)
            }

            optionsButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.delete -> {
                                listener.onDeleteListener(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEditListener(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
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
