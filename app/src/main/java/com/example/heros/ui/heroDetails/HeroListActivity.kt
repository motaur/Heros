package com.example.heros.ui.heroDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heros.R
import com.example.heros.databinding.ActivityHeroDetailsBinding
import com.example.heros.databinding.ActivityHeroListBinding
import com.example.heros.ui.heroList.heroDetails.HeroDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroDetailsBinding
    val vm: HeroDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.text.text = "my text"
    }
}