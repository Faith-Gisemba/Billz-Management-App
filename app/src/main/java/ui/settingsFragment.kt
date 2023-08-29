package ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.giseys.assessment.R
import utils.Constant

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val logout = view.findViewById<ImageView>(R.id.ivLog)
        logout.setOnClickListener {
            performLogout()
        }
        return view
    }
    private fun performLogout() {
        val sharedPrefs = requireActivity().getSharedPreferences(Constant.PREFS, Context.MODE_PRIVATE)

        val editor = sharedPrefs.edit()
        editor.clear().apply()

        val intent = Intent(requireContext(), logIn::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

    }


}
