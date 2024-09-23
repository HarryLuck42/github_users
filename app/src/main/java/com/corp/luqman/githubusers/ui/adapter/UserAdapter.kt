package com.corp.luqman.githubusers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.corp.luqman.githubusers.R
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.corp.luqman.githubusers.databinding.UserItemBinding

class UserAdapter(private val list: List<UserLocal>, private val listener: UserItemListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(private val binding: UserItemBinding, private val listener: UserItemListener): RecyclerView.ViewHolder(binding.root){
        fun bind(data: UserLocal){
            val itemView = binding.root
            Glide.with(itemView.context).load(data.avatarUrl).placeholder(R.drawable.loading_animation)
                .apply(
                    RequestOptions()
                    .error(R.drawable.ic_broken))
                .into(binding.ivUser)
            binding.tvUsername.text = data.username ?: "-"
            binding.itemCard.setOnClickListener {
                listener.onClickItem(data)
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: UserItemListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserItemBinding.inflate(layoutInflater)

                return ViewHolder(binding, listener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    interface UserItemListener{

        fun onClickItem(item: UserLocal)
    }


}

