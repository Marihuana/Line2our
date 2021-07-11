package com.four.hackerton.line2our.feature.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.four.hackerton.line2our.databinding.ItemTopicBinding
import com.four.hackerton.line2our.model.network.model.TopicVO

class StationTopicAdapter() : RecyclerView.Adapter<StationTopicAdapter.ViewHolder>() {
    var items = ArrayList<TopicVO>()
    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding : ItemTopicBinding): RecyclerView.ViewHolder(binding.root){
        fun bindViewHolder(topic : TopicVO){
            binding.item = topic

            Glide.with(binding.root.context)
                .load(topic.icon)
                .apply(RequestOptions().centerInside())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivTopic)

            binding.root.setOnClickListener {
                listener?.onItemClicked(topic)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(topic: TopicVO)
    }
}