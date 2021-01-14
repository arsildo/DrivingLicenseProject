package com.drivinglicenseapk.activities

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
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.exam_question_list_dialog.*
import kotlinx.android.synthetic.main.exam_result_dialog.*
import java.util.*
import java.util.concurrent.TimeUnit

class ExamActivity : AppCompatActivity(){
    private var ifExamEndedOnce = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)


        /* Start Counting Time Being Used and Exam Timer */
        chronometer.apply {
            base = SystemClock.elapsedRealtime()
            format = "%02d:%02d"
            start()
        }
        startExamTimer()


        /* Display Question List When clicked */
        questionListDialog.setOnClickListener {
            showQuestionList()
        }

        /* End Exam and Display Results */
        endExam.setOnClickListener {
            showTestResultDialog()
        }


    }

    private fun generateExam(){
        val questionStrings = arrayOf("Hello","Dude","What","Is","Up")
        val overallImage = R.drawable.img_overall
        val testImageOne = R.drawable.ic_back
        val testImageTwo = R.drawable.ic_github
        val questionImages = arrayOf(overallImage,testImageOne,testImageTwo,testImageOne)
        val questionAnswers = arrayOf(false,true,false,true,false)

    }


    override fun onBackPressed() {
        val exitPrompt = AlertDialog.Builder(this,R.style.AlertDialog)
        exitPrompt.setMessage("Jeni te sigurte qe doni te mbyllni provimin pa marre rezultatin?")

        exitPrompt.setPositiveButton("Po"){_,_ ->
            finish()
        }
        exitPrompt.setNegativeButton("Jo"){_,_ -> }
        val dialog: AlertDialog = exitPrompt.create()

        if (ifExamEndedOnce==0){
            dialog.show()
        }
        else {
            finish()
        }

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
                hide()
            }
        }
        questionListDialog.show()
    }

    private fun showTestResultDialog(){
        ifExamEndedOnce++
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
            val intent = Intent(this, ExamActivity::class.java)
            startActivity(intent)
            finish()
        }
        resultDialog.closeResults.setOnClickListener {
            finish()
        }
        resultDialog.reviewExam.setOnClickListener {
            resultDialog.hide()

            questionListDialog.setBackgroundColor(Color.parseColor("#0A870F"))
            questionListDialog.setImageResource(R.drawable.ic_questions_completed)

            endExam.setOnClickListener {
                val intent = Intent(this, ExamActivity::class.java)
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
