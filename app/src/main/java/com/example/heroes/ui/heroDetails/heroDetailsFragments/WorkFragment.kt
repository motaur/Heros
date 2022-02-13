package com.example.heroes.ui.heroDetails.heroDetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heroes.databinding.FragmentWorkBinding
import com.example.heroes.models.Work


class WorkFragment(private val work: Work) : Fragment() {

    private val binding: FragmentWorkBinding by lazy { FragmentWorkBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.occupation.text = work.occupation
        binding.base.text = work.base
    }

}