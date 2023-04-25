package com.example.shopaholic.BaseClasses

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.airbnb.lottie.LottieAnimationView
import com.example.shopaholic.R

class LoadingDialog {
    private var progressDialog: Dialog? = null

    fun initLoading(context: Context) {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.api_loading_dialog_layout)
        val viewById = dialog.findViewById(R.id.animation_view) as LottieAnimationView
        dialog.setCanceledOnTouchOutside(false)
        progressDialog = dialog

        //        cgxLoading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //        b.show();

//        progressDialog = Dialog(context)
//        progressDialog.setContentView(R.layout.flight_dialog_layout)

        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)

    }

    fun show(msg: String) {
        if (progressDialog != null) {
            if (!progressDialog!!.isShowing) {
                progressDialog!!.show()
            }
        }
    }

    fun isShowing(): Boolean {
        if (progressDialog != null) {
            return !progressDialog!!.isShowing
        }
        return false
    }

    fun show() {
        if (progressDialog != null) {
            if (!progressDialog!!.isShowing) {
                progressDialog!!.show()
            }
        }
    }

    fun dismiss() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        }
    }
}