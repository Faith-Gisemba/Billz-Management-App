
package com.giseys.assessment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.giseys.assessment.databinding.UpcomingbillsListItemBinding
import com.giseys.assessment.model.upcomingBill
import com.giseys.assessment.utils.DateTimeUtils

class UpcomingBillsAdapter(private val upcomingBills: List<upcomingBill>, val onClickBill: OnClickBill) :
    RecyclerView.Adapter<UpcomingBillsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingBillsViewHolder {
        val binding = UpcomingbillsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingBillsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return upcomingBills.size
    }

    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
        val upcomingBill = upcomingBills[position]
        holder.binding.apply {
            cbUpcoming.isChecked= upcomingBill.paid
            cbUpcoming.text = upcomingBill.name
            tvamount.text = upcomingBill.amount.toString()
            tvDuedate.text = DateTimeUtils.formatDateReadable(upcomingBill.dueDate)
        }
        holder.binding.cbUpcoming.setOnClickListener {
            onClickBill.onCheckBoxMarked(upcomingBill)
        }
    }
}

class UpcomingBillsViewHolder(var binding: UpcomingbillsListItemBinding) :
    ViewHolder(binding.root)



interface OnClickBill{
    fun onCheckBoxMarked(upcomingBill: upcomingBill)
}
