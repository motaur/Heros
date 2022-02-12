package com.example.heros.ui.heroList

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.heros.R
import com.example.heros.databinding.ActivityHeroListBinding
import com.example.heros.models.HeroUiModel
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.Duration

class HeroListActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHeroListBinding.inflate(layoutInflater) }
    val vm: HeroListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = vm
        binding.lifecycleOwner = this

        binding.etSearchFilter.imeOptions = EditorInfo.IME_ACTION_DONE

        binding.etSearchFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        vm.searchText = query
                        binding.etSearchFilter.clearFocus()
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String) = true
            }
        )

        vm.helper.observeNetwork(
            onAvailable = {
                vm.searchText = vm.searchText
                initSuggestions()
            },
            onLost = {}
        )
    }

    private fun initSuggestions() {
        vm.suggestions = emptyList()
        lifecycleScope.launch {
            vm.fetchSuggestions().let {
                vm.suggestions.forEach { heroUiModel ->
                    val chip = Chip(this@HeroListActivity).apply {
                        text = heroUiModel.name
                        chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.purple_200))

                    }
                    chip.setOnClickListener {
                        Toast.makeText(this@HeroListActivity, heroUiModel.id, Toast.LENGTH_SHORT).show()
                    }
                    binding.suggestions.addView(chip)
                }
            }
        }
    }
}