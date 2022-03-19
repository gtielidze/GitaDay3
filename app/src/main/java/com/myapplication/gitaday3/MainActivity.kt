package com.myapplication.gitaday3

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var selectDOB: Button
    private lateinit var selectTOR: Button
    private lateinit var saveBtn: Button


    private var mediaPlayer: MediaPlayer? = null


    private lateinit var qualificationTxt: TextView
    private lateinit var spinner: Spinner
    private val qualification =
        arrayOf("none", "1 years", "2 years", "2–5 years", "5–15 years", "15–30 years", "30 more")

    private lateinit var birthDayTxt: TextView
    private lateinit var registrationDayTxt: TextView
    private lateinit var nameTxt: EditText
    private lateinit var mobNumTxt: EditText
    private lateinit var checkBox: CheckBox

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDOB = findViewById(R.id.DOBSelectBtn)
        birthDayTxt = findViewById(R.id.birthDayField)
        selectDOB.setOnClickListener {
            showDayPickerDialog(birthDayTxt)
        }

        selectTOR = findViewById(R.id.TORSelectBtn)
        registrationDayTxt = findViewById(R.id.torField)
        selectTOR.setOnClickListener {
            showTimePickerDialog(registrationDayTxt)
        }
        nameTxt = findViewById(R.id.nameTxt)
        mobNumTxt = findViewById(R.id.numTxt)
        checkBox = findViewById(R.id.checkBox)


        qualificationTxt = findViewById(R.id.qualificationTxt)
        spinner = findViewById(R.id.qualificationSpinner)
        spinner.onItemSelectedListener = this
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qualification)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



        saveBtn = findViewById(R.id.saveButton)
        saveBtn.setOnClickListener {
            if (checkBox.isChecked) {
                nextScreen()
            } else {
                alertDialog()
            }

        }

        if (intent.getBooleanExtra("EXIT", false)) {
            finish();
        }

    }


    private fun showDayPickerDialog(textView: TextView) {
        val dayPickerDialog = DatePickerDialog(
            this, { _, year, month, dayOfMonth ->
                val result = "$dayOfMonth/${month + 1}/$year"
                textView.text = result
            },
            1990, 0, 1
        )
        dayPickerDialog.show()
    }

    private fun showTimePickerDialog(textView: TextView) {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hour, minute ->
                val result = "$hour:$minute"
                textView.text = result
            },
            13, 0, true
        )

        timePickerDialog.show()
    }


    private fun nextScreen() {
        val intent = Intent(this, ListViewActivity::class.java)
        intent.putExtra("birthDay", birthDayTxt.text.toString())
        intent.putExtra("registration time", registrationDayTxt.text.toString())
        intent.putExtra("name", nameTxt.text.toString())
        intent.putExtra("number", mobNumTxt.text.toString())
        intent.putExtra("qualification", qualificationTxt.text.toString())
        intent.putExtra("gender", getGender())
        startActivity(intent)
    }

    private fun getGender(): String {
        radioGroup = findViewById(R.id.genderRadioGroup)
        val selectedId = radioGroup.checkedRadioButtonId
        radioButton = findViewById<View>(selectedId) as RadioButton
        return radioButton.text.toString()
    }

    private fun playMedia() {
        mediaPlayer = MediaPlayer.create(this, R.raw.alert)
        mediaPlayer?.start()
    }

    private fun alertDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.show()
        playMedia()
        val decline = dialog.findViewById<Button>(R.id.dialogButton)
        decline.setOnClickListener { dialog.dismiss() }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val string = "Qualification: " + qualification[position]
        qualificationTxt.text = string
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}