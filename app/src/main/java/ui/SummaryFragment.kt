package ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giseys.assessment.R
import com.giseys.assessment.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {
    var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        binding?.ivAddFra?.setOnClickListener {
            startActivity(Intent(requireContext(), AddBillActivity::class.java))

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }


}