package com.example.heros.ui.heroDetails.heroDetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heros.databinding.FragmentWorkBinding
import com.example.heros.models.Work


class WorkFragment(val work: Work) : Fragment() {

    private val binding: FragmentWorkBinding by lazy { FragmentWorkBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        init()
    }

    private fun init(){
        binding.occupation.text = work.occupation
        binding.base.text = work.base
    }

}