package com.sd.searchticket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentCalendarBinding
import com.sd.searchticket.util.Args
import com.sd.searchticket.viewmodel.MainViewModel
import ru.cleverpumpkin.calendar.CalendarView

class CalendarFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val direction = arguments?.getString(Args.direction)

        binding.apply {

            calendarView.setupCalendar(selectionMode = CalendarView.SelectionMode.SINGLE)

            calendarView.onDateClickListener = { date ->

                val monthStr = if (date.month < 9) "0${date.month + 1}" else "${date.month + 1}"
                val dayStr =
                    if (date.dayOfMonth < 10) "0$date.dayOfMonth" else date.dayOfMonth.toString()
                val yearStr = date.year.toString()

                when (direction) {
                    Args.dep -> {
                        viewModel.setDateDep("$yearStr-$monthStr-$dayStr")
                        findNavController().navigate(R.id.countrySelectedFragment)
                    }

                    Args.arr -> {
                        if (viewModel.compareDate("$yearStr-$monthStr-$dayStr")) {
                            viewModel.setDateArr("$yearStr-$monthStr-$dayStr")
                            findNavController().navigate(R.id.countrySelectedFragment)
                        } else {
                            Toast.makeText(
                                context,
                                "Возвращение должно быть позднее отправления, а оно у Вас ${viewModel.dataDep.value}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        return binding.root
    }
}