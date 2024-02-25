package com.example.homework_26.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_26.R
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
                var circleNum = 0
                item.children?.let {
                    circleNum = getMaxCount(it, maxCount = 4)
                }
                repeat(circleNum) {
                    val circle = createCircleView()
                    binding.lcCircle.addView(circle)
                }
            }
        }

        private fun createCircleView(): View {
            val circleView = View(binding.root.context)
            val size = 10
            val layoutParams = LinearLayoutCompat.LayoutParams(size, size)
            layoutParams.marginEnd = 7
            circleView.layoutParams = layoutParams
            circleView.background =
                ContextCompat.getDrawable(binding.root.context, R.drawable.item_circle_shape)
            return circleView
        }

        private fun getMaxCount(data: List<ItemUi>, maxCount: Int = 4): Int {
            fun calculateMaxCount(node: ItemUi): Int {
                return if (node.children?.isEmpty()!!) {
                    1
                } else {
                    val childCounts = node.children.map { calculateMaxCount(it) }
                    1 + childCounts.maxOrNull()!!
                }
            }
            (data.maxOfOrNull { calculateMaxCount(it) } ?: 0).let {
                if (it > maxCount) {
                    return maxCount
                } else {
                    return it
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