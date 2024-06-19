package com.sd.searchticket.dto

data class Tickets(
    val id: Int,
    val badge: String?,
    val price: Price,
    val departure: DepArr,
    val arrival: DepArr,
    val has_transfer: Boolean
)
