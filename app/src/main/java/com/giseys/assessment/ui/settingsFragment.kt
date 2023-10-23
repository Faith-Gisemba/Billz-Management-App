package com.giseys.assessment.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.anychart.BuildConfig
import com.giseys.assessment.viewModel.BillViewModel
import com.giseys.assessment.R
import com.giseys.assessment.databinding.FragmentSettingsBinding
import com.giseys.assessment.utils.Constant

import com.google.android.material.snackbar.Snackbar

class SettingFragment : Fragment() {
    var binding: FragmentSettingsBinding? =null
    val billViewModel: BillViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val logout = view.findViewById<ImageView>(R.id.tvlogout)
        logout.setOnClickListener {
            performLogout()
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        binding?.tvAppVersion?.text= "APP VERSION: ${com.giseys.assessment.BuildConfig.VERSION_NAME}"
        binding?.tvlogout?.setOnClickListener{ performLogout()}
        binding?.tvsync?.setOnClickListener{ syncData()}


    }

    private fun performLogout() {
        val sharedPrefs = requireActivity().getSharedPreferences(Constant.PREFS, Context.MODE_PRIVATE)

        var editor = sharedPrefs.edit()
        editor= sharedPrefs.edit()
        editor.putString(Constant.USER_ID, Constant.EMPTY_STRING)
        editor.putString(Constant.ACCESS_TOKEN, Constant.EMPTY_STRING)
        editor.apply()

        val intent = Intent(requireContext(), logIn::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

    }

    fun syncData(){
//        Detect Network Connection(proceed where there is network connection)
        binding?.pbSync?.visibility= View.VISIBLE
        billViewModel.fetchRemoteBills()

        val timer= object : CountDownTimer(1000,100){
            override fun onTick(p0: Long) {
                TODO("Not yet implemented")
            }

            override fun onFinish() {
                binding?.pbSync
                Snackbar.make(binding!!.settingsRoot,"Sync Completed",Snackbar.LENGTH_LONG).show()
            }
        }
        timer.start()
    }

    override fun onDestroy(){
        super.onDestroy()
        binding = null
    }


}
