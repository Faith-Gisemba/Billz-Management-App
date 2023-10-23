package com.giseys.assessment.ui


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.graphics.vector.SolidFill
import com.giseys.assessment.databinding.FragmentSummaryBinding
import com.giseys.assessment.model.BillsSummary
import okhttp3.internal.Util
import com.giseys.assessment.utils.Utils
import com.giseys.assessment.viewModel.BillViewModel

class SummaryFragment : Fragment() {
    var binding: FragmentSummaryBinding?=null
    val billsViewModel: BillViewModel by viewModels()
    //    lateinit var summaryChartView: AnyChartView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))
        }
        billsViewModel.getMonthlySummary()
        showMonthlySummary()
    }
    fun showMonthlySummary(){
        billsViewModel.summaryLiveData.observe(this){summary->
            binding?.tvPaidAmt?.text= Utils.formatCurrency(summary.paid)
            binding?.tvOverdueAmt?.text= Utils.formatCurrency(summary.overdue)
            binding?.tvPendingAmt?.text= Utils.formatCurrency(summary.upcoming)
            binding?.tvTotalAmt?.text= Utils.formatCurrency(summary.total)
            ShowChart(summary)
        }
    }
    fun ShowChart(summary: BillsSummary){
        val entries = mutableListOf<DataEntry>()
        entries.add(ValueDataEntry("Paid",summary.paid))
        entries.add(ValueDataEntry("Upcoming",summary.upcoming))
        entries.add(ValueDataEntry("Overdue",summary.overdue))
        val pieChart = AnyChart.pie()
        pieChart.data(entries)
        pieChart.innerRadius(80)
        pieChart.palette().itemAt(0, SolidFill("#76ABDF",100))
        pieChart.palette().itemAt(1, SolidFill("#F89880",100))
        pieChart.palette().itemAt(2, SolidFill("#84AEAC",100))
        binding?.summaryChart?.setChart(pieChart)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}



















































































//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.giseys.assessment.R
//import com.giseys.assessment.databinding.FragmentSummaryBinding
//import model.BillsSummary
//import viewModel.BillViewModel
//import viewModel.UserViewModel
//
//class SummaryFragment : Fragment() {
//    var binding: FragmentSummaryBinding? = null
//
//    private lateinit var billsViewModel: BillViewModel
//    private lateinit var adapter: BillsAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        billsViewModel = ViewModelProvider(requireActivity()).get(BillViewModel::class.java)
//        adapter = BillsAdapter(requireContext(), R.layout.item_bill, mutableListOf())
//        binding?.listViewBills?.adapter = adapter
//        billsViewModel.getPaidBills().observe(viewLifecycleOwner, Observer { bills ->
//            adapter.clear()
//            adapter.addAll(bills)
//            adapter.notifyDataSetChanged()
//        })
//        binding?.fabAddBill?.setOnClickListener {
//            startActivity(Intent(requireContext(), AddBillActivity::class.java))
//        }
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSummaryBinding.inflate(inflater, container, false)
//        return binding?.root
//    }
//
//    override fun onResume() {
//        super.onResume()
//        binding?.fabAddBill?.setOnClickListener {
//            startActivity(Intent(requireContext(), AddBillActivity::class.java))
//
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding= null
//    }C
//
//    fun showChart(summary: BillsSummary){
//        val entries = mu
//
//    }
//
//
//}



