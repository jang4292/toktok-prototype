package com.example.toktok.ui

import android.app.Activity
import android.app.AlertDialog
import com.example.toktok.R

class CustomLoadingDialog(myActivity: Activity) {

    private var activity: Activity = myActivity
    private lateinit var dialog: AlertDialog

    fun startLoadingProgress() {

        val builder = AlertDialog.Builder(activity)

        val inflater = activity.layoutInflater;
        builder.setView(inflater.inflate(R.layout.custom_loading_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }


}