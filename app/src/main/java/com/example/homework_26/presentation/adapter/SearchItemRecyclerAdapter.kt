package com.example.homework_26.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.homework_26.databinding.SearchItemLayoutBinding
import com.example.homework_26.presentation.model.ItemUi

class SearchItemRecyclerAdapter :
    ListAdapter<ItemUi, SearchItemRecyclerAdapter.SearchItemViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ItemUi>() {
            override fun areItemsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class SearchItemViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                tvName.text = item.name
                item.children?.let { children ->
                    var num = 0
                    if(children.isNotEmpty()){
                        circle1.visibility = View.VISIBLE
                        children.map {
                            if(it.children?.isNotEmpty() == true) {
                                circle2.visibility = View.VISIBLE
                                it.children.map {
                                    if(it.children?.isNotEmpty() == true){
                                        circle3.visibility = View.VISIBLE
                                        it.children.map {
                                            if(it.children?.isNotEmpty() == true){
                                                circle4.visibility = View.VISIBLE
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            SearchItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind()
    }
}