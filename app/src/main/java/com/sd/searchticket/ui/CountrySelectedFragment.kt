package com.sd.searchticket.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentCountrySelectedBinding
import com.sd.searchticket.util.Args
import com.sd.searchticket.util.getDay
import com.sd.searchticket.util.separate
import com.sd.searchticket.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

class CountrySelectedFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCountrySelectedBinding.inflate(inflater, container, false)

        viewModel.fromCity.observe(viewLifecycleOwner) {
            binding.textFromCountry.text = it
        }
        viewModel.toCity.observe(viewLifecycleOwner) {
            binding.textToCountry.text = it
        }


        binding.apply {
            buttonSwapCountry.setOnClickListener {
                val text = textFromCountry.text.toString()
                viewModel.setFrom(textToCountry.text.toString())
                viewModel.setTo(text)
            }

            viewModel.dataTicketsOffer.observe(viewLifecycleOwner) {
                    if (it.tickets_offers.isNotEmpty() && it.tickets_offers.size >= 3) {
                        textAvia1.text = it.tickets_offers[0].title
                        textPrice1.text = "${it.tickets_offers[0].price.value.separate()} ₽ >"
                        textTime1.text = it.tickets_offers[0].time_range.joinToString(" ")

                        textAvia2.text = it.tickets_offers[1].title
                        textPrice2.text = "${it.tickets_offers[1].price.value.separate()} ₽ >"
                        textTime2.text = it.tickets_offers[1].time_range.joinToString(" ")

                        textAvia3.text = it.tickets_offers[2].title
                        textPrice3.text = "${it.tickets_offers[2].price.value.separate()} ₽ >"
                        textTime3.text = it.tickets_offers[2].time_range.joinToString(" ")
                    }
            }

            buttonDateDeparture.setOnClickListener {
                val dateBundle = Bundle()
                dateBundle.putString(Args.direction, Args.dep)
                findNavController().navigate(R.id.calendarFragment, dateBundle)
            }
            buttonDateBack.setOnClickListener {
                val dateBundle = Bundle()
                dateBundle.putString(Args.direction, Args.arr)
                findNavController().navigate(R.id.calendarFragment, dateBundle)
            }

            viewModel.dataDep.observe(viewLifecycleOwner) {
                val day = getDay(it)
                val dateN = LocalDate.parse(it).format(ofPattern("dd MMM"))
                buttonDateDeparture.text = "${dateN}, $day"
            }

            viewModel.dataArr.observe(viewLifecycleOwner) {
                if(!it.isNullOrEmpty()) {
                    val day = getDay(it)
                    val dateN = LocalDate.parse(it).format(ofPattern("dd MMM"))
                    buttonDateBack.text = "${dateN}, $day"
                } else {
                    buttonDateBack.text = "+ обратно"
                }
            }


//            val timeOrderDate = LocalDate.now().format(ofPattern("dd MMM"))
//
//            Log.d("MyLog", "timeOrderDate = $timeOrderDate, day = $day")
       //     val parseDate = dateNow DateTimeFormatter.ofPattern("dd MMMM,yyyy"))

//            calendarDeparture.setOnDateChangeListener { view, year, month, dayOfMonth ->
//               Log.d("MyLog","$dayOfMonth.${month + 1}.$year")
//            }

            buttonLookAtAllTickets.setOnClickListener {
                viewModel.loadTicket()
                findNavController().navigate(R.id.ticketsFragment)
            }
        }

        return binding.root
    }
}

