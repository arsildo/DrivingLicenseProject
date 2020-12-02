package com.drivinglicenseapk

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_screen.*
import kotlinx.android.synthetic.main.test_screen_list_dialog.*
import kotlinx.android.synthetic.main.test_screen_result_dialog.*
import java.util.*
import java.util.concurrent.TimeUnit

class TestScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_screen)



        /* End Test Button Behaviour */
        endTest_Button.setOnClickListener {
            showTestResultDialog()
        }


        /* Start Counting Time Being Used */
        val chronometer = findViewById<Chronometer>(R.id.hiddenChronometer)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.format = "%02d:%02d"
        chronometer.start()
        formatChronometer(chronometer)


        /* Start CountDownTimer from 40Min */
        object : CountDownTimer(2401000, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                testTimer.text= "" + millisUntilFinished/1000
                val testTimerInMinutes = String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                )
                testTimer.text = testTimerInMinutes
                // Test Result Window //
                endTest_Button.setOnClickListener {
                    showTestResultDialog()
                    cancel()

                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                cancel()
            }

        }.start()


        /* CheckBox Behaviour */
        trueCheckBox.setOnClickListener {
            falseCheckBox.isChecked = false
        }
        falseCheckBox.setOnClickListener {
            trueCheckBox.isChecked = false
        }


        /* Display Question List */
        val questionListButton = findViewById<Button>(R.id.showQuestionList)
        questionListButton.setOnClickListener {
            showQuestionList()
        }




    }

    /* Back Press Prompt */
    override fun onBackPressed() {
        val exitPrompt = AlertDialog.Builder(this,R.style.AlertDialog)
        exitPrompt.setMessage("Doni te mbyllni testin pa marre rezultate?")
        exitPrompt.setPositiveButton("Po"){_,_ ->
            finish()
        }
        exitPrompt.setNegativeButton("Jo"){_,_ ->

        }

        val dialog: AlertDialog = exitPrompt.create()
        dialog.show()
    }


    /* Format Chronometer String */
    private fun formatChronometer(chronometer: Chronometer) : String {
        val elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
        val time = elapsedTime / 1000
        val minutes = time / 60
        val seconds = time % 60
        return  String.format("%02d:%02d",minutes,seconds)
    }

    /* Display Question List Dialog */
    private fun showQuestionList(){
        val questionListDialog = Dialog(this)
        questionListDialog.setContentView(R.layout.test_screen_list_dialog)
        questionListDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        questionListDialog.setCancelable(true)
        questionListDialog.backToTestScreen.setOnClickListener {
            questionListDialog.hide()
        }
        questionListDialog.show()
    }

    /* Display Test Result Dialog */
    @SuppressLint("SetTextI18n")
    private fun showTestResultDialog(){
        val resultDialog = Dialog(this)
        resultDialog.setContentView(R.layout.test_screen_result_dialog)
        resultDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        resultDialog.setCancelable(false)

        // Show Time taken to finish
        resultDialog.testTimerResult.text = formatChronometer(findViewById(R.id.hiddenChronometer))

        // Buttons
        resultDialog.startNewTestButton.setOnClickListener {
            val intent = Intent(this,TestScreen::class.java)
            startActivity(intent)
            finish()
        }
        resultDialog.closeResultScreenButton.setOnClickListener {
            finish()
        }

        resultDialog.reviewMistakesButton.setOnClickListener {
            resultDialog.hide()

            showQuestionList.setBackgroundColor(Color.parseColor("#0A870F"))
            showQuestionList.setTextColor(Color.WHITE)

            endTest_Button.setOnClickListener {
                val intent = Intent(this,TestScreen::class.java)
                startActivity(intent)
                finish()
            }
            endTest_Button.text = "FILLO PROVIM TJETER"
            endTest_Button.setTextColor(Color.BLACK)
            endTest_Button.setBackgroundColor(Color.WHITE)
            endTest_Button.setBackgroundResource(R.drawable.test_screen_navigation_style)
            endTest_Button.setCompoundDrawablesWithIntrinsicBounds(0, 0,0,0)
        }



        resultDialog.show()
    }


}