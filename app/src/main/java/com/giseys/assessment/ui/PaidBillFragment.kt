package com.giseys.assessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.giseys.assessment.R
import com.giseys.assessment.databinding.FragmentPaidBillBinding
import com.giseys.assessment.model.upcomingBill
import com.giseys.assessment.viewModel.BillViewModel

class PaidBillFragment : Fragment(), OnClickBill {
    val billViewModel: BillViewModel by viewModels()
    var binding: FragmentPaidBillBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaidBillBinding.inflate(layoutInflater, container, false)
        return binding?.root

    }

    override fun onResume() {
        super.onResume()
        billViewModel.getPaidBills().observe(this){
            paidBills->
            val adapter= UpcomingBillsAdapter(paidBills,this)
            binding?.rvpaid?.layoutManager= LinearLayoutManager(requireContext())
            binding?.rvpaid?.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }

    override fun onCheckBoxMarked(upcomingBill: upcomingBill) {
        upcomingBill.paid= false
        billViewModel.updateUpcomingBill(upcomingBill)

        }
}
