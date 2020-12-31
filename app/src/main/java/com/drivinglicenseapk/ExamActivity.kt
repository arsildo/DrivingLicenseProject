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

        endExam.setOnClickListener {
            showTestResultDialog()
        }


        /* Start Counting Time Being Used */
        chronometer.apply {
            base = SystemClock.elapsedRealtime()
            format = "%02d:%02d"
            start()
        }
        formatChronometer(chronometer)

        /* Start CountDownTimer from 40Min */
        startExamTimer()

        /* CheckBox Behaviour */
        trueCheckBox.setOnClickListener {
            falseCheckBox.isChecked = false
        }
        falseCheckBox.setOnClickListener {
            trueCheckBox.isChecked = false
        }


        /* Display Question List */
        questionListDialog.setOnClickListener {
            showQuestionList()
        }


    }

    override fun onBackPressed() {
        val exitPrompt = AlertDialog.Builder(this,R.style.AlertDialog)
        exitPrompt.setMessage("Doni te mbyllni testin pa marrjen e rezultateve?")
        exitPrompt.setPositiveButton("Po"){_,_ ->
            finish()
        }
        exitPrompt.setNegativeButton("Jo"){_,_ -> }

        val dialog: AlertDialog = exitPrompt.create()
        dialog.show()
    }

    private fun startExamTimer(){
        object : CountDownTimer(2401000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                examTimer.text= "" + millisUntilFinished/1000
                val format = String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                )
                examTimer.text = format
                // Test Result Window //
                endExam.setOnClickListener {
                    showTestResultDialog()
                    cancel()

                }
            }
            override fun onFinish() {
                cancel()
            }

        }.start()
    }

    private fun formatChronometer(chronometer: Chronometer) : String {
        val elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
        val time = elapsedTime / 1000
        val minutes = time / 60
        val seconds = time % 60
        return  String.format("%02d:%02d",minutes,seconds)
    }

    private fun showQuestionList(){
        val questionListDialog = Dialog(this)
        questionListDialog.apply {
            setContentView(R.layout.exam_question_list_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(true)
            backToTestScreen.setOnClickListener {
                questionListDialog.hide()
            }
        }
        questionListDialog.show()
    }

    private fun showTestResultDialog(){
        val resultDialog = Dialog(this)
        resultDialog.apply {
            setContentView(R.layout.exam_result_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }
        // Show Time used text
        resultDialog.examChronometer.text = formatChronometer(chronometer)

        // Buttons
        resultDialog.startNewExam.setOnClickListener {
            val intent = Intent(this,ExamActivity::class.java)
            startActivity(intent)
            finish()
        }
        resultDialog.closeResults.setOnClickListener {
            finish()
        }
        resultDialog.reviewExam.setOnClickListener {
            resultDialog.hide()

            questionListDialog.setBackgroundColor(Color.parseColor("#0A870F"))
            questionListDialog.setTextColor(Color.WHITE)

            endExam.setOnClickListener {
                val intent = Intent(this,ExamActivity::class.java)
                startActivity(intent)
                finish()
            }
            endExam.apply {
                text = "FILLO PROVIM TJETER"
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.WHITE)
                setBackgroundResource(R.drawable.style_navigation)
                setCompoundDrawablesWithIntrinsicBounds(0, 0,0,0)
            }
        }



        resultDialog.show()
    }


}