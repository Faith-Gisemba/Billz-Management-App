package com.giseys.assessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.giseys.assessment.R
import com.giseys.assessment.databinding.FragmentUpcomingBillBinding
import com.giseys.assessment.model.upcomingBill
import com.giseys.assessment.utils.Constant
import com.giseys.assessment.viewModel.BillViewModel


class UpcomingBillFragment : Fragment() , OnClickBill {

    var binding : FragmentUpcomingBillBinding? = null
    val billViewModel: BillViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBillBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onResume() {
        super.onResume()
        getUpcomingBills()
//        billViewModel.createRecurringBills()
    }
    fun getUpcomingBills(){
        billViewModel.getUpcomingBillsByFre(Constant.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills, this)
                binding?.rvweekly?.layoutManager = LinearLayoutManager(requireContext())
                binding?.rvweekly?.adapter = adapter
            }

        billViewModel.getUpcomingBillsByFre(Constant.MONTHLY)
            .observe(this){monthlyBills->
                val adapter = UpcomingBillsAdapter(monthlyBills, this)
                binding?.rvweekly?.layoutManager = LinearLayoutManager(requireContext())
                binding?.rvweekly?.adapter = adapter
            }

        billViewModel.getUpcomingBillsByFre(Constant.YEARLY)
            .observe(this){annualBills->
                val adapter = UpcomingBillsAdapter(annualBills, this)
                binding?.rvweekly?.layoutManager = LinearLayoutManager(requireContext())
                binding?.rvweekly?.adapter = adapter
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCheckBoxMarked(upcomingBill: upcomingBill) {
        upcomingBill.paid = true
        billViewModel.updateUpcomingBill(upcomingBill)

    }


}