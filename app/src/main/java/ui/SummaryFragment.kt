package ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giseys.assessment.R
import com.giseys.assessment.databinding.FragmentSummaryBinding
import viewModel.BillViewModel
import viewModel.UserViewModel

class SummaryFragment : Fragment() {
    var binding: FragmentSummaryBinding? = null

    private lateinit var billsViewModel: BillViewModel
    private lateinit var adapter: BillsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billsViewModel = ViewModelProvider(requireActivity()).get(BillViewModel::class.java)
        adapter = BillsAdapter(requireContext(), R.layout.item_bill, mutableListOf())
        binding?.listViewBills?.adapter = adapter
        billsViewModel.getAllBills().observe(viewLifecycleOwner, Observer { bills ->
            adapter.clear()
            adapter.addAll(bills)
            adapter.notifyDataSetChanged()
        })
        binding?.ivAddFra?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        binding?.ivAddFra?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }


}



