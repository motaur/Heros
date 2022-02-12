package com.example.heros.ui.heroList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heros.R
import com.example.heros.databinding.HeroRowItemBinding
import com.example.heros.models.HeroUiModel

class HeroAdapter() : RecyclerView.Adapter<HeroAdapter.ItemHolder>() {

    private var listItemCallback: (HeroUiModel) -> Unit = {}

    private var dataSet = emptyList<HeroUiModel>()

    inner class ItemHolder(val binding: HeroRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                if (layoutPosition != RecyclerView.NO_POSITION)
                    listItemCallback(dataSet[layoutPosition])
            }
        }

        fun bind(item: HeroUiModel) {
            binding.apply {
                hero = item
                executePendingBindings()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listToUpdate: List<HeroUiModel>) {
        this.dataSet = listToUpdate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.hero_row_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        dataSet[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun setNavigationCallback(listItemCallback: (HeroUiModel) -> Unit){
        this.listItemCallback = listItemCallback
    }
}
