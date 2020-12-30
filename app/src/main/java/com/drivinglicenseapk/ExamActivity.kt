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
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.exam_question_list_dialog.*
import kotlinx.android.synthetic.main.exam_result_dialog.*
import java.util.*
import java.util.concurrent.TimeUnit

class ExamActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)



        /* End Test Button Behaviour */
        endTest_Button.setOnClickListener {
            showTestResultDialog()
        }


        /* Start Counting Time Being Used */
        hiddenChronometer.base = SystemClock.elapsedRealtime()
        hiddenChronometer.format = "%02d:%02d"
        hiddenChronometer.start()
        formatChronometer(hiddenChronometer)


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
        displayQuestionList.setOnClickListener {
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
        questionListDialog.setContentView(R.layout.exam_question_list_dialog)
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
        resultDialog.setContentView(R.layout.exam_result_dialog)
        resultDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        resultDialog.setCancelable(false)

        // Show Time taken to finish
        resultDialog.testTimerResult.text = formatChronometer(hiddenChronometer)

        // Buttons
        resultDialog.startNewTestButton.setOnClickListener {
            val intent = Intent(this,ExamActivity::class.java)
            startActivity(intent)
            finish()
        }
        resultDialog.closeResultScreenButton.setOnClickListener {
            finish()
        }

        resultDialog.reviewMistakesButton.setOnClickListener {
            resultDialog.hide()

            displayQuestionList.setBackgroundColor(Color.parseColor("#0A870F"))
            displayQuestionList.setTextColor(Color.WHITE)

            endTest_Button.setOnClickListener {
                val intent = Intent(this,ExamActivity::class.java)
                startActivity(intent)
                finish()
            }
            endTest_Button.text = "FILLO PROVIM TJETER"
            endTest_Button.setTextColor(Color.BLACK)
            endTest_Button.setBackgroundColor(Color.WHITE)
            endTest_Button.setBackgroundResource(R.drawable.style_navigation)
            endTest_Button.setCompoundDrawablesWithIntrinsicBounds(0, 0,0,0)
        }



        resultDialog.show()
    }


}