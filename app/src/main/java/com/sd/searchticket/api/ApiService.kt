package com.sd.searchticket.api

import com.sd.searchticket.dto.Offer
import com.sd.searchticket.dto.Ticket
import com.sd.searchticket.dto.TicketsOffer
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun loadOffer(): Response<Offer>

    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun loadTicketOffer(): Response<TicketsOffer>

    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun loadTicket(): Response<Ticket>
}