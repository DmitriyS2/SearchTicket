package com.sd.searchticket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding:FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)


        return binding.root
    }
}