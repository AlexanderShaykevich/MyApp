package ru.netology.nmedia.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.countView
import ru.netology.nmedia.databinding.PostBinding

typealias OnlikeListener = (post: Post) -> Unit
//typealias OnShareListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnlikeListener,
//    private val onShareListener: OnShareListener
) :
    RecyclerView.Adapter<ViewHolder>() {

    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onLikeListener,
//            onShareListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size


}


class ViewHolder(
    private val binding: PostBinding,
    private val onLikeListener: OnlikeListener,
//        private val onShareListener: OnShareListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) = with(binding) {
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
//                onShareListener(post)
            sharesAmount.text = countView(post.counterShare)
        }

        likesImage.setOnClickListener {
            onLikeListener(post)
            likesAmount.text = countView(post.counterLike)
        }
    }

}
