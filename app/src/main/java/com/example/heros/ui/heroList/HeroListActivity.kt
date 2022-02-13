package com.example.heros.ui.heroList

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.heros.Constants
import com.example.heros.databinding.ActivityHeroListBinding
import com.example.heros.models.HeroUiModel
import com.example.heros.ui.heroDetails.HeroDetailsActivity
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        vm.adapter.setNavigationCallback(::navigateToDetails)

        vm.networkHelper.observeNetwork(
            onAvailable = {
                vm.searchText = vm.searchText // initiate same search request
                initSuggestions()
            },
            onLost = {}
        )
    }

    private fun initSuggestions() {
        lifecycleScope.launch {
            vm.fetchSuggestions().let {
                vm.suggestions.forEach { heroUiModel ->
                    Chip(this@HeroListActivity).apply {
                        text = heroUiModel.name
                        setChipBackgroundColorResource(vm.getSuggestionColor())
                        setOnClickListener {
                            navigateToDetails(heroUiModel)
                        }
                        binding.suggestions.addView(this)
                    }
                }
            }
        }
    }

    private fun navigateToDetails(heroUiModel: HeroUiModel) =
        startActivity(Intent(this, HeroDetailsActivity::class.java).apply {
            putExtra(Constants.Keys.HERO_MODEL, heroUiModel)
        })

}