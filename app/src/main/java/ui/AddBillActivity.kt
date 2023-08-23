package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.giseys.assessment.R
import com.giseys.assessment.databinding.ActivityAddBillBinding
import viewModel.BillViewModel

class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billViewModel: BillViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_bill)
    }

    override fun onResume() {
        super.onResume()
    }

    fun setupFreqSpinner(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.frequencies,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spfrequency.adapter = adapter
    }

    fun setDueDateSpinner(){
        val weeklyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf(1,2,3,4,5,6,7))
        weeklyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = weeklyAdapter
    }
}