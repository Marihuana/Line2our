package com.four.hackerton.line2our.feature.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.four.hackerton.line2our.databinding.ItemPlaceBinding
import com.four.hackerton.line2our.feature.common.CustomCheckButton
import com.four.hackerton.line2our.model.network.model.PlaceVO

class PlaceListAdapter() : RecyclerView.Adapter<PlaceListAdapter.ViewHolder>() {
    var items = ArrayList<PlaceVO>()
    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding : ItemPlaceBinding): RecyclerView.ViewHolder(binding.root){
        fun bindViewHolder(place : PlaceVO, position : Int){
            binding.item = place
            binding.position = position.toString()
            binding.btnRecommend.setCount(place.likes)
            binding.btnBookMark.setCount(place.bookmarks)

            binding.root.setOnClickListener {
                listener?.onItemClicked(place)
            }

            binding.btnRecommend.setOnClickListener{
                binding.btnRecommend.setButtonChecked(!binding.btnRecommend.isChecked)
                listener?.onRecommendButtonClicked(binding.btnRecommend, place)
            }

            binding.btnBookMark.setOnClickListener {
                binding.btnBookMark.setButtonChecked(!binding.btnBookMark.isChecked)
                listener?.onBookMarkButtonClicked(binding.btnBookMark, place)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(place : PlaceVO)
        fun onRecommendButtonClicked(view : CustomCheckButton, place: PlaceVO)
        fun onBookMarkButtonClicked(view : CustomCheckButton, place: PlaceVO)
    }
}