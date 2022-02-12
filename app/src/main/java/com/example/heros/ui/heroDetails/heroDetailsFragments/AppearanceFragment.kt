package com.example.heros.ui.heroDetails.heroDetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heros.databinding.FragmentAppearanceBinding
import com.example.heros.models.Appearance

class AppearanceFragment(val appearance: Appearance) : Fragment() {

    private val binding: FragmentAppearanceBinding by lazy { FragmentAppearanceBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initAppearance()
    }

    private fun initAppearance(){
        binding.gender.text = appearance.gender
        binding.weight.text = appearance.weight[1]  ?: "-"
    }
}