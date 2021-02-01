package com.drivinglicenseapk.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.drivinglicenseapk.R
import com.drivinglicenseapk.handling.QuestionAdapter
import com.drivinglicenseapk.handling.QuestionData
import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.exam_question_list_dialog.*
import kotlinx.android.synthetic.main.exam_result_dialog.*
import java.util.*
import java.util.concurrent.TimeUnit


class ExamActivity : AppCompatActivity(){

    private var examState  = false
    private var alreadyPrompted = false
    private var alreadyTimed = false
    private var elapsedTimeStored = false
    private var elapsedTime : Long = 0

    private val questionData = QuestionData()
    private var  questionAdapter = QuestionAdapter(questionData)

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
            if (!alreadyTimed){
                start()
                alreadyTimed = true
            }else stop()
        }


        questionListDialog.setOnClickListener {
            showQuestionList()
        }

        endExam.setOnClickListener {
            Log.d("EXAM","$examState")
            if (examState){
                showExamResultDialog()
            }else promptExamEnd()
        }


        startExamTimer()

    }

    fun passExamState() : Boolean {
        return examState
    }


    override fun onBackPressed() {
        val exitPrompt = AlertDialog.Builder(this, R.style.AlertDialog)
        exitPrompt.setMessage("Jeni te sigurte qe doni te mbyllni provimin pa marre rezultatin?")

        exitPrompt.setPositiveButton("Po"){ _, _ ->
            finish()
        }
        exitPrompt.setNegativeButton("Jo"){ _, _ -> }
        val dialog: AlertDialog = exitPrompt.create()

        if (!examState){
            dialog.show()
        }
        else {
            finish()
        }
    }

    private fun promptExamEnd(){
        val endPrompt = AlertDialog.Builder(this, R.style.AlertDialog)
        endPrompt.setMessage("Perfundo  Provimin ?")
        endPrompt.setPositiveButton("Po"){ _, _->
            examState = true
            showExamResultDialog()
        }
        endPrompt.setNegativeButton("Jo"){ _, _-> }
        endPrompt.create()
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
                if (alreadyPrompted){
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

        if (!elapsedTimeStored){
            elapsedTime = SystemClock.elapsedRealtime()
            elapsedTimeStored = true
        }

        val elapsedTime = elapsedTime - chronometer.base
        val time = elapsedTime / 1000
        val minutes = time / 60
        val seconds = time % 60
        return  String.format(format = "%02d:%02d", minutes, seconds)
    }

    private fun showQuestionList(){
        val questionListDialog = Dialog(this)
        val questionViewPager = questionViewPager
        questionListDialog.apply {
            setContentView(R.layout.exam_question_list_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            marked_map.isVisible = false
            setCancelable(true)
            backToTestScreen.setOnClickListener {
                hide()
            }
            val markedMistakes = questionAdapter.markMistakes()
            if (!examState){
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
            else{
                questionListDialog.marked_map.isVisible = true
                if (markedMistakes[0]==0){
                    question_1.setTextColor(Color.WHITE)
                    question_1.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_1.setTextColor(Color.WHITE)
                    question_1.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[1]==0){
                    question_2.setTextColor(Color.WHITE)
                    question_2.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_2.setTextColor(Color.WHITE)
                    question_2.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[2]==0){
                    question_3.setTextColor(Color.WHITE)
                    question_3.setBackgroundResource(R.drawable.style_button_mistake)
                }
                else{
                    question_3.setTextColor(Color.WHITE)
                    question_3.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[3]==0){
                    question_4.setTextColor(Color.WHITE)
                    question_4.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_4.setTextColor(Color.WHITE)
                    question_4.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[4]==0){
                    question_5.setTextColor(Color.WHITE)
                    question_5.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_5.setTextColor(Color.WHITE)
                    question_5.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[5]==0){
                    question_6.setTextColor(Color.WHITE)
                    question_6.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_6.setTextColor(Color.WHITE)
                    question_6.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[6]==0){
                    question_7.setTextColor(Color.WHITE)
                    question_7.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_7.setTextColor(Color.WHITE)
                    question_7.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[7]==0){
                    question_8.setTextColor(Color.WHITE)
                    question_8.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_8.setTextColor(Color.WHITE)
                    question_8.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[8]==0){
                    question_9.setTextColor(Color.WHITE)
                    question_9.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_9.setTextColor(Color.WHITE)
                    question_9.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[9]==0){
                    question_10.setTextColor(Color.WHITE)
                    question_10.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_10.setTextColor(Color.WHITE)
                    question_10.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[10]==0){
                    question_11.setTextColor(Color.WHITE)
                    question_11.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_11.setTextColor(Color.WHITE)
                    question_11.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[11]==0){
                    question_12.setTextColor(Color.WHITE)
                    question_12.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_12.setTextColor(Color.WHITE)
                    question_12.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[12]==0){
                    question_13.setTextColor(Color.WHITE)
                    question_13.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_13.setTextColor(Color.WHITE)
                    question_13.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[13]==0){
                    question_14.setTextColor(Color.WHITE)
                    question_14.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_14.setTextColor(Color.WHITE)
                    question_14.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[14]==0){
                    question_15.setTextColor(Color.WHITE)
                    question_15.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_15.setTextColor(Color.WHITE)
                    question_15.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[15]==0){
                    question_16.setTextColor(Color.WHITE)
                    question_16.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_16.setTextColor(Color.WHITE)
                    question_16.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[16]==0){
                    question_17.setTextColor(Color.WHITE)
                    question_17.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_17.setTextColor(Color.WHITE)
                    question_17.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[17]==0){
                    question_18.setTextColor(Color.WHITE)
                    question_18.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_18.setTextColor(Color.WHITE)
                    question_18.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[18]==0){
                    question_19.setTextColor(Color.WHITE)
                    question_19.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_19.setTextColor(Color.WHITE)
                    question_19.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[19]==0){
                    question_20.setTextColor(Color.WHITE)
                    question_20.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_20.setTextColor(Color.WHITE)
                    question_20.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[20]==0){
                    question_21.setTextColor(Color.WHITE)
                    question_21.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_21.setTextColor(Color.WHITE)
                    question_21.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[21]==0){
                    question_22.setTextColor(Color.WHITE)
                    question_22.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_22.setTextColor(Color.WHITE)
                    question_22.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[22]==0){
                    question_23.setTextColor(Color.WHITE)
                    question_23.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_23.setTextColor(Color.WHITE)
                    question_23.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[23]==0){
                    question_24.setTextColor(Color.WHITE)
                    question_24.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_24.setTextColor(Color.WHITE)
                    question_24.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[24]==0){
                    question_25.setTextColor(Color.WHITE)
                    question_25.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_25.setTextColor(Color.WHITE)
                    question_25.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[25]==0){
                    question_26.setTextColor(Color.WHITE)
                    question_26.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_26.setTextColor(Color.WHITE)
                    question_26.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[26]==0){
                    question_27.setTextColor(Color.WHITE)
                    question_27.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_27.setTextColor(Color.WHITE)
                    question_27.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[27]==0){
                    question_28.setTextColor(Color.WHITE)
                    question_28.setBackgroundResource(R.drawable.style_button_mistake)
                }
                else{
                    question_28.setTextColor(Color.WHITE)
                    question_28.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[28]==0){
                    question_29.setTextColor(Color.WHITE)
                    question_29.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_29.setTextColor(Color.WHITE)
                    question_29.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[29]==0){
                    question_30.setTextColor(Color.WHITE)
                    question_30.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_30.setTextColor(Color.WHITE)
                    question_30.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[30]==0){
                    question_31.setTextColor(Color.WHITE)
                    question_31.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_31.setTextColor(Color.WHITE)
                    question_31.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[31]==0){
                    question_32.setTextColor(Color.WHITE)
                    question_32.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_32.setTextColor(Color.WHITE)
                    question_32.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[32]==0){
                    question_33.setTextColor(Color.WHITE)
                    question_33.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_33.setTextColor(Color.WHITE)
                    question_33.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[33]==0){
                    question_34.setTextColor(Color.WHITE)
                    question_34.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_34.setTextColor(Color.WHITE)
                    question_34.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[34]==0){
                    question_35.setTextColor(Color.WHITE)
                    question_35.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_35.setTextColor(Color.WHITE)
                    question_35.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[35]==0){
                    question_36.setTextColor(Color.WHITE)
                    question_36.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_36.setTextColor(Color.WHITE)
                    question_36.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[36]==0){
                    question_37.setTextColor(Color.WHITE)
                    question_37.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_37.setTextColor(Color.WHITE)
                    question_37.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[37]==0){
                    question_38.setTextColor(Color.WHITE)
                    question_38.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_38.setTextColor(Color.WHITE)
                    question_38.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[38]==0){
                    question_39.setTextColor(Color.WHITE)
                    question_39.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_39.setTextColor(Color.WHITE)
                    question_39.setBackgroundResource(R.drawable.style_button_correct)
                }
                if (markedMistakes[39]==0){
                    question_40.setTextColor(Color.WHITE)
                    question_40.setBackgroundResource(R.drawable.style_button_mistake)
                }else{
                    question_40.setTextColor(Color.WHITE)
                    question_40.setBackgroundResource(R.drawable.style_button_correct)
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
        }
        questionListDialog.show()
    }

    fun showExamResultDialog(){
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
            finishAndRemoveTask()
        }
        resultDialog.reviewExam.setOnClickListener {
            showQuestionList()
            resultDialog.hide()

            questionListDialog.apply {
                setBackgroundColor(Color.parseColor("#0A870F"))
                setImageResource(R.drawable.ic_questions_completed)
            }

            endExam.apply {
                text = "KTHEHU NE MENU"
                setTextColor(Color.BLACK)
                setBackgroundColor(Color.WHITE)
                setBackgroundResource(R.drawable.style_navigation)
                setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }

        }
        resultDialog.show()
    }


}
