package com.sd.searchticket.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sd.searchticket.R
import com.sd.searchticket.adapter.TicketAdapter
import com.sd.searchticket.databinding.FragmentTicketsBinding
import com.sd.searchticket.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TicketsFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTicketsBinding.inflate(inflater, container, false)

        val adapter = TicketAdapter()

        binding.apply {
            textInfoFlight.text = "${viewModel.fromCity.value} - ${viewModel.toCity.value}"
            val dateArr = LocalDate.parse(viewModel.dataDep.value)
            textInfoDataPassenger.text =
                "${dateArr.format(DateTimeFormatter.ofPattern("dd MMMM"))}, 1 пассажир"

            rvTickets.layoutManager = LinearLayoutManager(activity)
            rvTickets.adapter = adapter

            viewModel.dataTicket.observe(viewLifecycleOwner) {
                adapter.submitList(it.tickets)
            }

            imageBack.setOnClickListener {
                findNavController().navigate(R.id.countrySelectedFragment)
            }
        }

        return binding.root
    }

}