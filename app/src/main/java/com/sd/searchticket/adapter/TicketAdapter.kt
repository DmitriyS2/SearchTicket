package com.sd.searchticket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sd.searchticket.R
import com.sd.searchticket.databinding.ItemTicketBinding
import com.sd.searchticket.dto.Tickets
import com.sd.searchticket.util.separate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class TicketAdapter : ListAdapter<Tickets, TicketAdapter.TicketHolder>(TicketDiffUtil()) {
    class TicketHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemTicketBinding.bind(item)

        fun bind(ticket: Tickets) = with(binding) {
            cvBadge.isVisible = ticket.badge != null
            textBadge.text = ticket.badge
            textPriceItem.text = "${ticket.price.value.separate()} ₽"

            val dateDepart = LocalDateTime.parse((ticket.departure.date))
            val hourDepart = dateDepart.hour
            val minDepart = dateDepart.minute
            val timeDepart = LocalTime.of(hourDepart, minDepart)

            val dateArr = LocalDateTime.parse((ticket.arrival.date))
            val hourArr = dateArr.hour
            val minArr = dateArr.minute
            val timeArr = LocalTime.of(hourArr, minArr)

            val timeFlyMin = ChronoUnit.MINUTES.between(dateDepart, dateArr)
            val partOfHour = when (timeFlyMin % 60) {
                in 0..14 -> 0.0
                in 15..45 -> 0.5
                in 46..59 -> 1.0
                else -> 0.0
            }
            val timeFlyHours: String = when (partOfHour) {
                0.0 -> "$timeFlyMin/60"
                0.5 -> "${timeFlyMin / 60 + 0.5}"
                1.0 -> "${timeFlyMin / 60 + 1}"
                else -> ""
            }

            val transfer = if (!ticket.has_transfer) " / Без пересадок" else ""

            textInfoItem.text = "$timeDepart - $timeArr ${timeFlyHours}ч в пути$transfer"
            textAeroports.text = "${ticket.departure.airport}  ${ticket.arrival.airport}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return TicketHolder(view)
    }

    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        val ticket = getItem(position)
        holder.bind(ticket)
    }
}

class TicketDiffUtil : DiffUtil.ItemCallback<Tickets>() {
    override fun areItemsTheSame(oldItem: Tickets, newItem: Tickets): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tickets, newItem: Tickets): Boolean {
        return oldItem == newItem
    }

}