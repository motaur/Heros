package com.example.heros.ui.heroList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heros.R
import com.example.heros.databinding.ActivityHeroListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroListBinding
    val vm: HeroListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.text.text = "my text"
    }
}