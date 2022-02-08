package com.example.heros.ui.heroList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.example.heros.databinding.ActivityHeroListBinding
import com.example.heros.models.HeroUiModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroListBinding
    val vm: HeroListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.heroesList.adapter = vm.adapter
        binding.etSearchFilter.imeOptions = EditorInfo.IME_ACTION_DONE
        binding.etSearchFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.etSearchFilter.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    vm.searchText = newText
                    return true
                }
            }
        )
        lifecycleScope.launch{
            vm.init()
        }
    }

}