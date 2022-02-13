package com.example.heroes.ui.heroDetails.heroDetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heroes.databinding.FragmentAppearanceBinding
import com.example.heroes.models.Appearance

class AppearanceFragment(private val appearance: Appearance) : Fragment() {

    private val binding: FragmentAppearanceBinding by lazy { FragmentAppearanceBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppearance()
    }

    private fun initAppearance(){
        binding.gender.text = appearance.gender
        try {
            binding.weight.text = appearance.weight[0]
        }
        catch (e: IndexOutOfBoundsException){
            binding.weight.text = "-"
        }
    }
}