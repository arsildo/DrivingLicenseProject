package com.drivinglicenseapk.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.drivinglicenseapk.R
import com.drivinglicenseapk.handling.QuestionData
import com.drivinglicenseapk.handling.QuestionViewPagerAdapter
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.exam_question_list_dialog.*
import kotlinx.android.synthetic.main.exam_result_dialog.*
import kotlinx.android.synthetic.main.item_question.*
import kotlinx.android.synthetic.main.item_question.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class ExamActivity : AppCompatActivity(){

    private var ifExamAlreadyEnded = 0

    private val questionData = QuestionData()
    private var  questionAdapter = QuestionViewPagerAdapter(questionData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        questionViewPager.adapter = questionAdapter

        questionViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                ((position + 1).toString() + "/40").also { currentQuestionIndicator.text = it }
            }

        })

        nextQuestion.setOnClickListener {
            questionViewPager.currentItem = questionViewPager.currentItem+1
        }
        previousQuestion.setOnClickListener {
            questionViewPager.currentItem = questionViewPager.currentItem-1
        }





        chronometer.apply {
            base = SystemClock.elapsedRealtime()
            format = "%02d:%02d"
            start()
        }
        startExamTimer()



        questionListDialog.setOnClickListener {
            showQuestionList()
        }


        endExam.setOnClickListener {
            showExamResultDialog()
        }


    }


    override fun onBackPressed() {
        val exitPrompt = AlertDialog.Builder(this,R.style.AlertDialog)
        exitPrompt.setMessage("Jeni te sigurte qe doni te mbyllni provimin pa marre rezultatin?")

        exitPrompt.setPositiveButton("Po"){_,_ ->
            finish()
        }
        exitPrompt.setNegativeButton("Jo"){_,_ -> }
        val dialog: AlertDialog = exitPrompt.create()

        if (ifExamAlreadyEnded==0){
            dialog.show()
        }
        else {
            finish()
        }

    }


    private fun startExamTimer(){
        object : CountDownTimer(2401000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                examTimer.text = "${millisUntilFinished / 1000}"
                val format = String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                )
                examTimer.text = format
                // Test Result Window //
                endExam.setOnClickListener {
                    showExamResultDialog()
                    cancel()

                }
            }
            override fun onFinish() {
                showExamResultDialog()
                cancel()
            }

        }.start()
    }

    private fun formatChronometer(chronometer: Chronometer) : String {
        val elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
        val time = elapsedTime / 1000
        val minutes = time / 60
        val seconds = time % 60
        return  String.format(format = "%02d:%02d",minutes,seconds)
    }

    private fun showQuestionList(){
        val questionListDialog = Dialog(this)
        val questionViewPager = questionViewPager
        questionListDialog.apply {
            setContentView(R.layout.exam_question_list_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(true)
            backToTestScreen.setOnClickListener {
                hide()
            }
            question_1.setOnClickListener {
                hide()
                questionViewPager.currentItem = 0
            }

            question_2.setOnClickListener {
                hide()
                questionViewPager.currentItem = 1
            }

            question_3.setOnClickListener {
                hide()
                questionViewPager.currentItem = 2
            }

            question_4.setOnClickListener {
                hide()
                questionViewPager.currentItem = 3
            }

            question_5.setOnClickListener {
                hide()
                questionViewPager.currentItem = 4
            }

            question_6.setOnClickListener {
                hide()
                questionViewPager.currentItem = 5
            }

            question_7.setOnClickListener {
                hide()
                questionViewPager.currentItem = 6
            }

            question_8.setOnClickListener {
                hide()
                questionViewPager.currentItem = 7
            }

            question_9.setOnClickListener {
                hide()
                questionViewPager.currentItem = 8
            }

        }
        questionListDialog.show()
    }

    private fun showExamResultDialog(){
        ifExamAlreadyEnded++
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
