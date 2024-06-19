package com.sd.searchticket.repository

import com.sd.searchticket.dto.Offer
import com.sd.searchticket.dto.Ticket
import com.sd.searchticket.dto.TicketsOffer

interface Repository {
    suspend fun loadOffer(): Offer?

    suspend fun loadTicketOffer(): TicketsOffer?

    suspend fun loadTicket(): Ticket?

    fun getFrom(): String?

    fun getTo(): String?

    fun setFrom(from: String)

    fun setTo(to: String)


}