package com.sd.searchticket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentBlankBackBinding

class BlankBackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBlankBackBinding.inflate(inflater, container, false)

        binding.buttonBackFromBlanc.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

        return binding.root
    }

}