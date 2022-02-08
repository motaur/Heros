package com.example.heros.ui.heroList

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heros.R
import com.example.heros.databinding.HeroRowItemBinding
import com.example.heros.models.HeroUiModel

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.ItemHolder>() {

    private var list: List<HeroUiModel> = emptyList()

    class ItemHolder(val binding: HeroRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.heroName?.let { photo ->
//                    val uri = Uri.parse(photo.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    view.context.startActivity(intent)
                }
            }
        }
    }

    private fun filter(searchText: String) =
        arrayListOf<HeroUiModel>().also { filteredList ->
            list.forEach {
                if (it.name.contains(searchText, ignoreCase = true))
                    filteredList.add(it)
            }
        }

    fun setList(listToUpdate: List<HeroUiModel>, searchText: String? = null) {

        if (searchText == null || searchText.isEmpty()) {
            this.list = listToUpdate
        } else
            this.list = filter(searchText)

        notifyDataSetChanged() //TODO change
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
        list[position].run {
            holder.binding.heroName.text = this.name
        }
    }

    override fun getItemCount(): Int = list.size
}
