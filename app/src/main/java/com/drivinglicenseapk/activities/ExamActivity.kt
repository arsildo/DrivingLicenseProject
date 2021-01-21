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
import androidx.viewpager2.widget.ViewPager2
import com.drivinglicenseapk.R
import com.drivinglicenseapk.handling.QuestionData
import com.drivinglicenseapk.handling.QuestionViewPagerAdapter
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.exam_question_list_dialog.*
import kotlinx.android.synthetic.main.exam_result_dialog.*
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

    private fun promptExamEnd(){
        val endPrompt = AlertDialog.Builder(this, R.style.AlertDialog)
        endPrompt.setMessage("Perfundo  Provimin ?")
        endPrompt.setPositiveButton("Po"){_,_->
            showExamResultDialog()
        }
        endPrompt.setNegativeButton("Jo"){_,_-> }
        val dialog : AlertDialog = endPrompt.create()
        endPrompt.show()
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
                    promptExamEnd()
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
            question_10.setOnClickListener {
                hide()
                questionViewPager.currentItem = 9
            }
            question_11.setOnClickListener {
                hide()
                questionViewPager.currentItem = 10
            }
            question_12.setOnClickListener {
                hide()
                questionViewPager.currentItem = 11
            }
            question_13.setOnClickListener {
                hide()
                questionViewPager.currentItem = 12
            }
            question_14.setOnClickListener {
                hide()
                questionViewPager.currentItem = 13
            }
            question_15.setOnClickListener {
                hide()
                questionViewPager.currentItem = 14
            }
            question_16.setOnClickListener {
                hide()
                questionViewPager.currentItem = 15
            }
            question_17.setOnClickListener {
                hide()
                questionViewPager.currentItem = 16
            }
            question_18.setOnClickListener {
                hide()
                questionViewPager.currentItem = 17
            }
            question_19.setOnClickListener {
                hide()
                questionViewPager.currentItem = 18
            }
            question_20.setOnClickListener {
                hide()
                questionViewPager.currentItem = 19
            }
            question_21.setOnClickListener {
                hide()
                questionViewPager.currentItem = 20
            }
            question_22.setOnClickListener {
                hide()
                questionViewPager.currentItem = 21
            }
            question_23.setOnClickListener {
                hide()
                questionViewPager.currentItem = 22
            }
            question_24.setOnClickListener {
                hide()
                questionViewPager.currentItem = 23
            }
            question_25.setOnClickListener {
                hide()
                questionViewPager.currentItem = 24
            }
            question_26.setOnClickListener {
                hide()
                questionViewPager.currentItem = 25
            }
            question_27.setOnClickListener {
                hide()
                questionViewPager.currentItem = 26
            }
            question_28.setOnClickListener {
                hide()
                questionViewPager.currentItem = 27
            }
            question_29.setOnClickListener {
                hide()
                questionViewPager.currentItem = 28
            }
            question_30.setOnClickListener {
                hide()
                questionViewPager.currentItem = 29
            }
            question_31.setOnClickListener {
                hide()
                questionViewPager.currentItem = 30
            }
            question_32.setOnClickListener {
                hide()
                questionViewPager.currentItem = 31
            }
            question_33.setOnClickListener {
                hide()
                questionViewPager.currentItem = 32
            }
            question_34.setOnClickListener {
                hide()
                questionViewPager.currentItem = 33
            }
            question_35.setOnClickListener {
                hide()
                questionViewPager.currentItem = 34
            }
            question_36.setOnClickListener {
                hide()
                questionViewPager.currentItem = 35
            }
            question_37.setOnClickListener {
                hide()
                questionViewPager.currentItem = 36
            }
            question_38.setOnClickListener {
                hide()
                questionViewPager.currentItem = 37
            }
            question_39.setOnClickListener {
                hide()
                questionViewPager.currentItem = 38
            }
            question_40.setOnClickListener {
                hide()
                questionViewPager.currentItem = 39
            }
        }
        questionListDialog.show()
    }

    fun showExamResultDialog(){
        ifExamAlreadyEnded++
        val questionViewPager = questionViewPager
        questionViewPager.isEnabled = false
        val resultDialog = Dialog(this)
        resultDialog.apply {
            setContentView(R.layout.exam_result_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
        }

        val numberOfMistakes = questionAdapter.countMistakes()
        if (numberOfMistakes>4){
            ("$numberOfMistakes Gabime").also { resultDialog.numberOfMistakes.text = it }
            resultDialog.numberOfMistakes.setTextColor(Color.RED)
        }else{
            ("$numberOfMistakes Gabime").also { resultDialog.numberOfMistakes.text = it }
            resultDialog.numberOfMistakes.setTextColor(Color.GREEN)
        }



        resultDialog.examChronometer.text = formatChronometer(chronometer)

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


            questionViewPager.isClickable = false

            questionListDialog.apply {
                setBackgroundColor(Color.parseColor("#0A870F"))
                setImageResource(R.drawable.ic_questions_completed)
            }

            endExam.setOnClickListener {
                val intent = Intent(this, ExamActivity::class.java)
                startActivity(intent)
            }
            endExam.apply {
                text = "FILLO PROVIM TE RI"
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.WHITE)
                setBackgroundResource(R.drawable.style_navigation)
                setCompoundDrawablesWithIntrinsicBounds(0, 0,0,0)
            }
        }

        resultDialog.show()
    }

}
