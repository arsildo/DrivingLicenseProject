package com.drivinglicenseapk

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_screen.*
import kotlinx.android.synthetic.main.activity_select_screen.view.*
import kotlinx.android.synthetic.main.select_screen_information_dialog.*

class SelectScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_screen)

        /* Open Test Screen */
        startTest.setOnClickListener {
            val intent = Intent(this,TestScreen::class.java)
            startActivity(intent)
        }


        /* Dev Info */
        showInformationDialog.setOnClickListener {
            showDevInformation()
        }
    }

    private fun showDevInformation(){
        val devInformationDialog = Dialog(this)
        devInformationDialog.setContentView(R.layout.select_screen_information_dialog)
        devInformationDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        devInformationDialog.setCancelable(true)

        devInformationDialog.closeInformationDialog.setOnClickListener {
            devInformationDialog.hide()
        }
        devInformationDialog.githubLink.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/arsildo"))
            startActivity(openUrl)
        }


        devInformationDialog.show()
    }

}