package com.example.heros.ui.heroDetails.heroDetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.heros.databinding.FragmentConnectionsBinding
import com.example.heros.models.Connections

class ConnectionsFragment(val connections: Connections) : Fragment() {

    private val binding: FragmentConnectionsBinding by lazy { FragmentConnectionsBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initConnections()
    }

    private fun initConnections(){
        binding.relatives.text = connections.relatives
        binding.group.text = connections.groupAffiliation
    }

}