package com.sd.searchticket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sd.searchticket.dto.Offer
import com.sd.searchticket.dto.Ticket
import com.sd.searchticket.dto.TicketsOffer
import com.sd.searchticket.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _fromCity = MutableLiveData<String>()
    val fromCity: LiveData<String>
        get() = _fromCity

    private val _toCity = MutableLiveData<String>()
    val toCity: LiveData<String>
        get() = _toCity

    private val _dataOffer = MutableLiveData<Offer>()
    val dataOffer: LiveData<Offer>
        get() = _dataOffer

    private val _dataTicketsOffer = MutableLiveData<TicketsOffer>()
    val dataTicketsOffer: LiveData<TicketsOffer>
        get() = _dataTicketsOffer

    private val _dataTicket = MutableLiveData<Ticket>()
    val dataTicket: LiveData<Ticket>
        get() = _dataTicket

    private val _dataDep = MutableLiveData<String>()
    val dataDep: LiveData<String>
        get() = _dataDep

    private val _dataArr = MutableLiveData<String>()
    val dataArr: LiveData<String>
        get() = _dataArr

    init {
        _fromCity.value = repository.getFrom()
        _toCity.value = repository.getTo()
        _dataDep.value = LocalDate.now().toString()

        loadOffer()
    }

    private fun loadOffer() {
        viewModelScope.launch {
            try {
                _dataOffer.value = repository.loadOffer()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadTicketOffer() {
        viewModelScope.launch {
            try {
                _dataTicketsOffer.value = repository.loadTicketOffer()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadTicket() {
        viewModelScope.launch {
            try {
                _dataTicket.value = repository.loadTicket()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setFrom(from: String) {
        repository.setFrom(from)
        _fromCity.value = from
    }

    fun setTo(to: String) {
        repository.setTo(to)
        _toCity.value = to
    }

    fun compareDate(date: String): Boolean {
        val dateDep = LocalDate.parse(_dataDep.value)
        val dateArr = LocalDate.parse(date)
        return dateDep < dateArr
    }

    fun setDateDep(date: String) {
        _dataDep.value = date
    }

    fun setDateArr(date: String) {
        _dataArr.value = date
    }
}