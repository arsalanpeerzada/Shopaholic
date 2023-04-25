package com.example.shopaholic.BaseClasses

import android.R
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {
  //  var addressModelList: List<DeliverAddressModel> = ArrayList<DeliverAddressModel>()

    fun dismissDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) dialog.dismiss()
    }



    fun showDialog(dialog: Dialog?) {
//        if (dialog != null && !dialog.isShowing()) dialog.show();
    }

//    fun progressDialog(context: Context?): Dialog? {
//        val dialog = Dialog(context!!)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.progress_layout)
//        dialog.setCancelable(false)
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        return dialog
//    }

    fun showToastMessage(message: String?, context: Context?) {
        // if (message == null || message.equals("")) return;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//        View toastView = toast.getView();
//        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
//        toastMessage.setTextColor(Color.WHITE);
//        toastMessage.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher_round, 0, 0, 0);
//        toastMessage.setGravity(Gravity.CENTER);
//        toastMessage.setCompoundDrawablePadding(16);
//        final int sdk = android.os.Build.VERSION.SDK_INT;
//        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            toastView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_design) );
//        } else {
//            toastView.setBackground(ContextCompat.getDrawable(context, R.drawable.button_design));
//        }
//        toast.show();
    }

    fun e(tag: String?, msg: String?) {
        Log.e(tag, msg!!)
    }


    fun isValidEmailed(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun checknullandSetValueofString(datavalue: String?, tobesetOn: TextView) {
        if (datavalue != null && datavalue != "") {
            tobesetOn.text = "" + datavalue
        } else {
            tobesetOn.text = ""
        }
    }


    fun showTimeIn24Format(textView: TextView, starttime: String?) {
        if (starttime != null && starttime != "") {
            val dateStr: String = starttime
            val readFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ssaa")
            val writeFormat: DateFormat = SimpleDateFormat("EEE dd MMM yyyy hh:mm aa")
            readFormat.timeZone = TimeZone.getTimeZone("UTC")
            var date: Date? = null
            try {
                date = readFormat.parse(dateStr)
                var formattedDate = ""
                if (date != null) {
                    formattedDate = writeFormat.format(date)
                }
                println(formattedDate)
                println("Date: " + formattedDate.substring(0, formattedDate.indexOf(' ')))
                println("Time: " + formattedDate.substring(formattedDate.indexOf(' ') + 1))

                //            String day =new SimpleDateFormat("EEEE").format(starttime);
                textView.text = formattedDate.substring(
                    0,
                    formattedDate.indexOf(' ')
                ) + " " + formattedDate.substring(formattedDate.indexOf(' ') + 1)
            } catch (e: ParseException) {
                e.printStackTrace()
                textView.text = "-"
            }
        }
    }

    fun SpinnerAdapter(
        arrayList: ArrayList<*>?,
        spinner: Spinner,
        selectPos: Int,
        context: Context?
    ) {
        if (arrayList != null) {
            val adapter: ArrayAdapter<*> =
                ArrayAdapter(context!!, R.layout.simple_spinner_item, arrayList)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.setSelection(selectPos)
        }
    }

    fun isInternetConnected(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connection = manager.activeNetworkInfo
        return if (connection != null && connection.isConnectedOrConnecting) {
            true
        } else false
    }

    var LOGIN_TYPE = ""
    var LOGIN_ID = ""
}