package ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.giseys.assessment.databinding.ActivityMainBinding
import model.RegisterRequest
import utils.Constant
import viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()
    }
    override fun onResume() {
        super.onResume()
        binding.btnLogIn.setOnClickListener {
            validateregdetails()
        }
        userViewModel.errLiveData.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })
        userViewModel.regiLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
            binding.pbRegister.visibility = View.GONE
        })
    }
    private   fun validateregdetails() {
        clearErrors()
        val firstname = binding.etFirstName.text.toString()
        val lastname = binding.etLastName.text.toString()
        val phoneNumber= binding.etPhoneNumber.text.toString()
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassWord.text.toString()
        val confPassword = binding.etConfirmPassWord.text.toString()
        var error = false
        if (firstname.isBlank()) {
            error = true
            binding.tilFirstName.error = " FirstName required"
        }
        if (lastname.isBlank()) {
            error = true
            binding.tilLastName.error = " LastName required"
        }
        if (phoneNumber.isBlank()) {
            error = true
            binding.tilPhoneNumber.error = " Phone number required"
        }
        if (email.isBlank()) {
            error = true
            binding.tilEmailAddress.error = " Create password"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassWord.error = " Invalid password"
        }
        if (!password.equals(confPassword)) {
            error = true
            binding.tilConfirmPassword.error = " PasswordWord does not match"
        }
        if (!error) {
            val registerRequest = RegisterRequest(
                first_name = firstname,
                last_name = lastname,
                email = email,
                password = password,
                phone_number = phoneNumber
            )
            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }
    fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilLastName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilPassWord.error = null
        binding.tilConfirmPassword.error = null
    }
    fun redirectUser(){
        val sharedPrefs = getSharedPreferences(Constant.PREFS, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getString(Constant.USER_ID, Constant.EMPTY_STRING)
        if (userId.isNullOrBlank()){
            startActivity(Intent(this, logIn::class.java))
        }
        else{
            startActivity(Intent(this, HomeActivity::class.java))
        }
        finish()
    }
}