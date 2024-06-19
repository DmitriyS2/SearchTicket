package com.sd.searchticket.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentSearchBinding
import com.sd.searchticket.viewmodel.MainViewModel

class SearchFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinding =
            FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {

            viewModel.fromCity.observe(viewLifecycleOwner) {
                editFrom2.setText(it.toString())
            }

            viewModel.toCity.observe(viewLifecycleOwner) {
                editTo2.setText(it.toString())
            }

            buttonCancelEditTo2.setOnClickListener {
                viewModel.setTo("")
            }

            cvRoute.setOnClickListener {
                findNavController().navigate(R.id.blankBackFragment)
            }
            cvWorld.setOnClickListener {
                viewModel.setTo("Куда угодно")
                viewModel.loadTicketOffer()
                findNavController().navigate(R.id.countrySelectedFragment)
            }
            cvCalendar.setOnClickListener {
                findNavController().navigate(R.id.blankBackFragment)
            }
            cvHot.setOnClickListener {
                findNavController().navigate(R.id.blankBackFragment)
            }

            cvStambul.setOnClickListener {
                viewModel.setTo("Стамбул")
                viewModel.loadTicketOffer()
                findNavController().navigate(R.id.countrySelectedFragment)
            }
            cvSochi.setOnClickListener {
                viewModel.setTo("Сочи")
                viewModel.loadTicketOffer()
                findNavController().navigate(R.id.countrySelectedFragment)
            }
            cvPhuket.setOnClickListener {
                viewModel.setTo("Пхукет")
                viewModel.loadTicketOffer()
                findNavController().navigate(R.id.countrySelectedFragment)
            }

            editFrom2.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        viewModel.setFrom(editFrom2.text.toString())
                        editFrom2.clearFocus()
                        editFrom2.isCursorVisible = false
                        return true
                    }
                    return false
                }
            })

            editTo2.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        viewModel.setTo(editTo2.text.toString())
                        editTo2.clearFocus()
                        if (!editTo2.text.isNullOrEmpty()) {
                            viewModel.loadTicketOffer()
                            findNavController().navigate(R.id.countrySelectedFragment)
                        }
                        return true
                    }
                    return false
                }
            })
        }
        return binding.root
    }
}

