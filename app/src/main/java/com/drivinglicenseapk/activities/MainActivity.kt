package com.drivinglicenseapk.activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val themeSetting : SharedPreferences = getSharedPreferences("themeSetting", MODE_PRIVATE)
        val themeSettingEditor : SharedPreferences.Editor = themeSetting.edit()
        val themeSettingCheck : Boolean = themeSetting.getBoolean("DarkMode",false)


        if (themeSettingCheck){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            gitHubLink.setImageResource(R.drawable.ic_github_dark)
            themeToggle.setImageResource(R.drawable.ic_light_mode)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            gitHubLink.setImageResource(R.drawable.ic_github_light)
            themeToggle.setImageResource(R.drawable.ic_dark_mode)
        }

        themeToggle.setOnClickListener {
            if (themeSettingCheck){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                gitHubLink.setImageResource(R.drawable.ic_github_dark)
                themeToggle.setImageResource(R.drawable.ic_light_mode)
                themeSettingEditor.putBoolean("DarkMode",false)
                themeSettingEditor.apply()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                gitHubLink.setImageResource(R.drawable.ic_github_light)
                themeToggle.setImageResource(R.drawable.ic_dark_mode)
                themeSettingEditor.putBoolean("DarkMode",true)
                themeSettingEditor.apply()
            }
        }



        openExam.setOnClickListener {
            val intent = Intent(this, ExamActivity::class.java)
            startActivity(intent)
        }

        gitHubLink.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/arsildo"))
            startActivity(openUrl)
        }
    }
}