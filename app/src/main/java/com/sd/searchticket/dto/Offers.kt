package com.sd.searchticket.dto

data class Offers(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)