package com.example.sharedpreference

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var btnSave: Button
    private lateinit var btnRetrieve: Button
    private lateinit var tvResult: TextView

    private val PREF_NAME = "Myprefs"
    private val KEY_NAME = "name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        btnSave = findViewById(R.id.btnSave)
        btnRetrieve = findViewById(R.id.btnRetrieve)
        tvResult = findViewById(R.id.tvResult)

        btnSave.setOnClickListener {
            val name = etUsername.text.toString()
            if(name.isNotEmpty()){
                saveNameToPreferences(name)
            }else{
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }

        btnRetrieve.setOnClickListener {
            val savedName = retrieveNameFromPreferences()
            if(savedName.isNotEmpty()){
                tvResult.text = "Retrieved Name: $savedName"
            }else{
                tvResult.text = "No Name saved yet!"
            }
        }
    }

    private fun saveNameToPreferences(name: String){
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
        Toast.makeText(this, "Name saved successfully", Toast.LENGTH_SHORT).show()
    }

    private fun retrieveNameFromPreferences(): String{
        val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NAME, "") ?: ""
    }
}