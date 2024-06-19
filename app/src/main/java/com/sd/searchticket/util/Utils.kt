package com.sd.searchticket.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.DayOfWeek
import java.time.LocalDate

val formatter = DecimalFormat()
val symbols: DecimalFormatSymbols = DecimalFormatSymbols.getInstance()

fun Int.separate(): String = formatter.format(this).toString()

fun getDay(date: String): String {
    return when (LocalDate.parse(date)?.dayOfWeek) {
        DayOfWeek.MONDAY -> "пон"
        DayOfWeek.TUESDAY -> "вт"
        DayOfWeek.WEDNESDAY -> "ср"
        DayOfWeek.THURSDAY -> "четв"
        DayOfWeek.FRIDAY -> "пятн"
        DayOfWeek.SATURDAY -> "сб"
        DayOfWeek.SUNDAY -> "вскр"
        else -> ""
    }
}

object Args {
    const val direction = "direction"
    const val dep = "departure"
    const val arr = "arrival"
}