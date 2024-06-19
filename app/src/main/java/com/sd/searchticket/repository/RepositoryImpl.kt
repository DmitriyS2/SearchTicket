package com.sd.searchticket.repository

import android.content.Context
import com.sd.searchticket.api.ApiService
import com.sd.searchticket.dto.Offer
import com.sd.searchticket.dto.Ticket
import com.sd.searchticket.dto.TicketsOffer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService
) : Repository {

    private val prefs = context.getSharedPreferences("searchTickets", Context.MODE_PRIVATE)
    private val fromKey = "from"
    private val toKey = "to"

    override fun getFrom(): String? = prefs.getString(fromKey, null)

    override fun getTo(): String? = prefs.getString(toKey, null)

    override fun setFrom(from: String) {
        with(prefs.edit()) {
            putString(fromKey, from)
            apply()
        }
    }

    override fun setTo(to: String) {
        with(prefs.edit()) {
            putString(toKey, to)
            apply()
        }
    }

    override suspend fun loadTicketOffer(): TicketsOffer? {
        try {
            val response = apiService.loadTicketOffer()
            if (!response.isSuccessful) {
                return null
            }
            return response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun loadTicket(): Ticket? {
        try {
            val response = apiService.loadTicket()
            if (!response.isSuccessful) {
                return null
            }
            return response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun loadOffer(): Offer? {
        try {
            val response = apiService.loadOffer()
            if (!response.isSuccessful) {
                return null
            }
            return response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}