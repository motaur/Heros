package com.example.heros.ui.heroList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.heros.R
import com.example.heros.databinding.HeroRowItemBinding
import com.example.heros.models.HeroUiModel

class HeroesAdapter(private var dataSet: Array<HeroUiModel>) : RecyclerView.Adapter<HeroesAdapter.ItemHolder>() {

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

    private fun filter(searchText: String, listToUpdate: Array<HeroUiModel>): Array<HeroUiModel> =
        arrayListOf<HeroUiModel>().also { filteredList ->
            listToUpdate.forEach {
                if (it.name.contains(searchText, ignoreCase = true))
                    filteredList.add(it)
            }
        }.toTypedArray()

    fun setList(listToUpdate: Array<HeroUiModel>, searchText: String? = null) {

        if (searchText == null || searchText.isEmpty()) {
            this.dataSet = listToUpdate
        } else
            this.dataSet = filter(searchText, listToUpdate)

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
        holder.binding.heroName.text = dataSet[position].name
    }

    override fun getItemCount(): Int = dataSet.size
}
