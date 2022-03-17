package com.myapplication.gitaday3

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var selectDOB: Button
    private lateinit var selectTOR: Button
    private lateinit var saveBtn: Button

    private lateinit var birthDayTxt: TextView
    private lateinit var registrationDayTxt: TextView
    private lateinit var nameTxt: EditText
    private lateinit var mobNumTxt: EditText
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDOB = findViewById(R.id.DOBSelectBtn)
        birthDayTxt = findViewById(R.id.birthDayField)
        selectDOB.setOnClickListener {
            dayPicker(birthDayTxt)
        }

        selectTOR = findViewById(R.id.TORSelectBtn)
        registrationDayTxt = findViewById(R.id.torField)
        selectTOR.setOnClickListener {
            dayPicker(registrationDayTxt)
        }
        nameTxt = findViewById(R.id.nameTxt)
        mobNumTxt = findViewById(R.id.numTxt)
        checkBox = findViewById(R.id.checkBox)
        saveBtn = findViewById(R.id.saveButton)




        saveBtn.setOnClickListener {
            if (checkBox.isChecked) {
                nextScreen()
            } else {
                alertDialog()
            }
        }
    }


    private fun dayPicker(textView: TextView) {
        val dayPickerDialog = DatePickerDialog(
            this, { _, year, month, dayOfMonth ->
                val result = "$dayOfMonth/${month + 1}/$year"
                textView.text = result
            },
            1990, 0, 1
        )
        dayPickerDialog.show()
    }

    private fun nextScreen() {
        val intent = Intent(this, NextActivity::class.java)
        intent.putExtra("birthDay", birthDayTxt.text.toString())
        intent.putExtra("registration time", registrationDayTxt.text.toString())
        intent.putExtra("name", nameTxt.text.toString())
        intent.putExtra("number", mobNumTxt.text.toString())
        startActivity(intent)
    }




    private fun alertDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.show()
        val decline = dialog.findViewById<Button>(R.id.dialogButton)
        decline.setOnClickListener { dialog.dismiss() }
    }

}