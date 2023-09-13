package ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.giseys.assessment.R
import com.giseys.assessment.databinding.ActivityAddBillBinding
import model.Bill
import utils.Constant
import viewModel.BillViewModel
import java.util.Objects
import java.util.UUID

class AddBillActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billViewModel: BillViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFreqSpinner()
        setDueDateSpinner()

        binding.btnSaveBill.setOnClickListener {
            val selectedFrequency = binding.spfrequency.selectedItem.toString()
            val billName = binding.etName.text.toString()
            val billAmount = binding.etAmount.text.toString().toDouble()
            val selectedDueDate: String = when (selectedFrequency) {
                "Annual" -> {
                    val datePicker = binding.dpDueDateAnnual
                    "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"
                }
                else -> binding.spinner2.selectedItem.toString()
            }

            val bill = Bill(
                billId = UUID.randomUUID().toString(),
                name = billName,
                amount = billAmount,
                frequency = selectedFrequency,
                dueDate = selectedDueDate,
                userId = "USER_ID"
            )
            billViewModel.saveBill(bill)
            finish()
            navigateToSummaryFragment()
        }
    }
    fun setupFreqSpinner(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.frequencies,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spfrequency.adapter = adapter
    }
    fun View.show(){
        this.visibility = View.VISIBLE
    }
    fun View.hide(){
        this.visibility = View.GONE
    }
    private fun setDueDateSpinner() {
        binding.spfrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFrequency = binding.spfrequency.selectedItem.toString()

                val dueDateAdapter = when (binding.spfrequency.selectedItem.toString()) {
                    "Weekly" -> {
                        val daysInWeek = 1..7
                        ArrayAdapter(
                            this@AddBillActivity,
                            android.R.layout.simple_spinner_item,
                            daysInWeek.toList()
                        )
                    }

                    "Monthly" -> {
                        val daysInMonth = 1..31
                        ArrayAdapter(
                            this@AddBillActivity,
                            android.R.layout.simple_spinner_item,
                            daysInMonth.toList()
                        )
                    }

                    "Quarterly" -> {
                        val daysInQuarter = 1..90
                        ArrayAdapter(
                            this@AddBillActivity,
                            android.R.layout.simple_spinner_item,
                            daysInQuarter.toList()
                        )
                    }

                    "Annual" -> {
                        binding.spfrequency.visibility = View.GONE
                        binding.dpDueDateAnnual.visibility = View.VISIBLE
                        val daysInYear = 1..365
                        ArrayAdapter(
                            this@AddBillActivity,
                            android.R.layout.simple_spinner_item,
                        )

                    }

                    else -> {
                        ArrayAdapter(
                            this@AddBillActivity,
                            android.R.layout.simple_spinner_item,
                            arrayOf(1, 2, 3, 4, 5, 6, 7)
                        )

                    }
                }
                dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner2.adapter = dueDateAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun navigateToSummaryFragment(){
        val fragment = SummaryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

}







