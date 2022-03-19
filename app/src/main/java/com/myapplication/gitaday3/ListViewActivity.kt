package com.myapplication.gitaday3

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var arrayList = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)



        listView = findViewById(R.id.listView)
        addData()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            parent.getItemAtPosition(3)
            alertDialog()
        }

    }
    private fun addData() {
        intent.getStringExtra("birthDay")?.let { arrayList.add("Birthday: $it") }
        intent.getStringExtra("registration time")?.let { arrayList.add("Time of registration: $it") }
        intent.getStringExtra("name")?.let { arrayList.add("Name: $it") }
        intent.getStringExtra("number")?.let { arrayList.add("Number: $it") }
        intent.getStringExtra("qualification")?.let { arrayList.add("Qualification: $it") }
        intent.getStringExtra("gender")?.let { arrayList.add("Gender: $it") }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    fun finish(item: MenuItem) {
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        startActivity(intent)
    }

    fun openWebPage(item: MenuItem) {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)
    }
    private fun alertDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.navigate)
        dialog.show()
        val navigateBtn = dialog.findViewById<Button>(R.id.navigateButton)
        navigateBtn.setOnClickListener { openEmptyActivity() }
    }
    private fun openEmptyActivity() {
        val intent = Intent(this, EmptyActivity::class.java)
        startActivity(intent)
    }
}