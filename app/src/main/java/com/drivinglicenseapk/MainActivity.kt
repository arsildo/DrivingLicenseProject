package com.drivinglicenseapk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Open Test Screen */
        startTest.setOnClickListener {
            val intent = Intent(this,ExamActivity::class.java)
            startActivity(intent)
        }


        /* Dev Info */
        showInformationDialog.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/arsildo"))
            startActivity(openUrl)
        }
    }



}