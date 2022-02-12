package com.example.heros.ui.heroDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.heros.Constants
import com.example.heros.databinding.ActivityHeroDetailsBinding
import com.example.heros.models.HeroUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailsActivity : AppCompatActivity() {
    private val binding by lazy {ActivityHeroDetailsBinding.inflate(layoutInflater)}
        val vm: HeroDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewModel = vm
        binding.lifecycleOwner = this

        vm.heroUiModel = intent.getSerializableExtra(Constants.Keys.HERO_MODEL)!! as HeroUiModel
    }
}