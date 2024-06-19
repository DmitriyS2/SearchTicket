package com.sd.searchticket.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sd.searchticket.R
import com.sd.searchticket.databinding.FragmentMainBinding
import com.sd.searchticket.util.separate
import com.sd.searchticket.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private val listImage = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.apply {

            viewModel.fromCity.observe(viewLifecycleOwner) {
                if (it == null || it == "") {
                    editFrom.hint = "Откуда - Москва"
                } else {
                    editFrom.setText(viewModel.fromCity.value)
                }
            }

            viewModel.toCity.observe(viewLifecycleOwner) {
                if (it == null || it == "") {
                    editTo.hint = "Куда - Турция"
                } else {
                    editTo.setText(viewModel.toCity.value)
                }
            }

            viewModel.dataOffer.observe(viewLifecycleOwner) {
                it?.let {
                    image1MuzOtletet.setImageResource(listImage[it.offers[0].id - 1])
                    textTitle1.text = it.offers[0].title
                    textCity1.text = it.offers[0].town
                    textPrice1MuzOtletet.text = "от ${it.offers[0].price.value.separate()} ₽"

                    image2MuzOtletet.setImageResource(listImage[it.offers[1].id - 1])
                    textTitle2.text = it.offers[1].title
                    textCity2.text = it.offers[1].town
                    textPrice2MuzOtletet.text = "от ${it.offers[1].price.value.separate()} ₽"

                    image3MuzOtletet.setImageResource(listImage[it.offers[2].id - 1])
                    textTitle3.text = it.offers[2].title
                    textCity3.text = it.offers[2].town
                    textPrice3MuzOtletet.text = "от ${it.offers[2].price.value.separate()} ₽"
                }
            }

            editFrom.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        viewModel.setFrom(editFrom.text.toString())
                        editFrom.clearFocus()
                        return true
                    }
                    return false
                }
            })

            editTo.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    editTo.clearFocus()
                    findNavController().navigate(R.id.searchFragment)
                }
            }
        }
        return binding.root
    }
}