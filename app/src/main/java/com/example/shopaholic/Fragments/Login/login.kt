package com.example.shopaholic.Fragments.Login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.shopaholic.Activities.HomeActivity
import com.example.shopaholic.Activities.SignUp
import com.example.shopaholic.Models.Login.LoginResponseModel
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import com.mukesh.OtpView
import com.mukesh.tinydb.TinyDB
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class login : Fragment() {

    lateinit var next: Button
    lateinit var myview: View
    lateinit var register: TextView
    lateinit var ccp: CountryCodePicker
    lateinit var edtnumber: EditText
    lateinit var confirmotp: Button
    var p_number: String? = null
    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)
    lateinit var tinydb: TinyDB

    private val code: String? = null
    private lateinit var timer: TextView
    private lateinit var resend: Button
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_login, container, false)
        next = myview.findViewById(R.id.proceed)
        register = myview.findViewById(R.id.registerLogin)
        ccp = myview.findViewById(R.id.ccp)
        edtnumber = myview.findViewById(R.id.edtnumber)

        ccp.registerPhoneNumberTextView(edtnumber)
        tinydb = TinyDB(requireContext())

        register.setOnClickListener {
            startActivity(Intent(requireContext(), SignUp::class.java))
        }

        next.setOnClickListener {
            next.setEnabled(false)
            if (edtnumber.getText().toString().trim { it <= ' ' } != "") {
                p_number = edtnumber.getText().toString()
                if (ccp.isValid()) {
                    if (p_number!!.startsWith("0")) {
                        p_number = p_number!!.substring(1)
                        Log.d("Sign In", "Button Clicked")
                        Login(ccp.selectedCountryCodeWithPlus + p_number)
                    } else {
                        Log.d("Sign In", "Button Clicked 2")
                        Login(ccp.selectedCountryCodeWithPlus + p_number)
                    }
                } else {
                    next.setEnabled(true)
                    Toast.makeText(requireContext(), "Number is Not Valid", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                next.setEnabled(true)
                Toast.makeText(requireContext(), "Enter Number", Toast.LENGTH_SHORT).show()
            }

        }
        return myview
    }

    private fun showOtpDialog(number: String, code: String) {
        val viewGroup: ViewGroup = requireActivity().findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(requireContext()).inflate(
                R.layout.otp_code_dailog,
                viewGroup,
                false
            )
        dialogView.setBackgroundColor(Color.TRANSPARENT)
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)
        builder.setView(dialogView)
        timer = dialogView.findViewById<TextView>(R.id.timer)
        resend = dialogView.findViewById<Button>(R.id.resend)
        confirmotp = dialogView.findViewById<Button>(R.id.confirmotp)
        val otpView: OtpView = dialogView.findViewById(R.id.otp_view)
        otpView.setText(code)
        val alertDialog = builder.create()
        resend.setOnClickListener {
            Toast.makeText(requireContext(), "Resend", Toast.LENGTH_SHORT).show()
        }
        confirmotp.setOnClickListener {

            confirmotp.isEnabled = false
            checkOTP(number, code)

//            openFragment(alertDialog)
        }
        startTimer(timer, resend)
        alertDialog.show()
    }

    private fun openFragment(alertDialog: AlertDialog) {

        alertDialog.dismiss()

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.backgroundLayout, Password())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    private fun startTimer(timer: TextView, resend: Button) {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "" + String.format(
                    "0%d : %02d ",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    millisUntilFinished
                                )
                            )
                )
            }

            override fun onFinish() {
                countDownTimer!!.cancel()
                timer.visibility = View.GONE
                resend.visibility = View.VISIBLE
            }
        }.start()
    }

    var login: Call<LoginResponseModel>? = null
    fun Login(phoneNumber: String) {
        login = webservice?.Login(phoneNumber)

        login?.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status == "done") {
                        showOtpDialog(phoneNumber, response.body()?.otp!!)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    var checkOtp: Call<LoginResponseModel>? = null
    fun checkOTP(phoneNumber: String, otp: String) {
        checkOtp = webservice?.VerifyOtp(phoneNumber, otp)
        checkOtp?.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.status == "matched") {

                        tinydb.putString("number", phoneNumber)
                        tinydb.putString("otp", otp)
                        tinydb.putString("userid", response.body()?.userId)
                        Toast.makeText(requireContext(), "Authenticated", Toast.LENGTH_SHORT).show()
                        requireContext().startActivity(
                            Intent(
                                requireContext(),
                                HomeActivity::class.java
                            )
                        )
                    } else {
                        Toast.makeText(requireContext(), "Number is Not Valid", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                Toast.makeText(requireContext(), "Number is Not Valid", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

}