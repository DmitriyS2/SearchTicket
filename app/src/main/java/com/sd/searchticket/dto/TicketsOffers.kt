package com.sd.searchticket.dto

data class TicketsOffers(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
)
