package com.myapplication.gitaday3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.*

class NextActivity : AppCompatActivity() {
    private lateinit var birth: TextView
    private lateinit var registration: TextView
    private lateinit var name: TextView
    private lateinit var number: TextView

    private lateinit var confirm: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        fieldsInitialization()

        confirm = findViewById(R.id.confirmBtn)
        confirm.setOnClickListener {
            val string =
                "your birthday is ${birth.text} and you registered in ${registration.text}, " +
                        "your name is  ${name.text}, phone number ${number.text}"
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }

    }

    private fun fieldsInitialization() {
        birth = findViewById(R.id.birthdayTxt)
        birth.text = intent.getStringExtra("birthDay")
        registration = findViewById(R.id.regDayTxt)
        registration.text = intent.getStringExtra("registration time")
        name = findViewById(R.id.nameSecondTxt)
        name.text = intent.getStringExtra("name")
        number = findViewById(R.id.numberSecondTxt)
        number.text = intent.getStringExtra("number")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun finish(item: MenuItem) {
        finish()
    }

    fun openWebPage(item: MenuItem) {

        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)

    }

}