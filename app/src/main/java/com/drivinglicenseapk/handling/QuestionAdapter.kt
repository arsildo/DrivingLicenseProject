package com.drivinglicenseapk.handling

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import com.drivinglicenseapk.activities.ExamActivity
import kotlinx.android.synthetic.main.item_question.view.*


class QuestionAdapter(private val questionData: QuestionData) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewPagerViewHolder>() {

    private val checkBoxStateA = BooleanArray(40)
    private val checkBoxStateB = BooleanArray(40)
    private var userGivenAnswers = arrayOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    )
    private var mistakes=0
    private var ifExamEnded = 0

    inner class QuestionViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var qString: TextView = itemView.questionString
        var qImage : ImageView = itemView.questionImage
        var chTrue : CheckBox = itemView.trueCheckBox
        var chFalse : CheckBox = itemView.falseCheckBox

        init {


            if (ifExamEnded==0){
                chTrue.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        chFalse.isChecked = false
                        checkBoxStateA[adapterPosition] = true
                        userGivenAnswers[adapterPosition] = "Sakte"
                    }else {
                        checkBoxStateA[adapterPosition] = false
                    }
                }

                chFalse.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        chTrue.isChecked = false
                        checkBoxStateB[adapterPosition] = true
                        userGivenAnswers[adapterPosition] = "Gabim"
                    }else{
                        checkBoxStateB[adapterPosition] = false
                    }

                }
            }else{
                chTrue.isClickable = false
                chTrue.isClickable = false
            }


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewPagerViewHolder {
        return QuestionViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuestionViewPagerViewHolder, position: Int) {

        val qString = holder.qString
        val qImage = holder.qImage
        val chTrue = holder.chTrue
        val chFalse = holder.chFalse

        qString.text = questionData.questionStrings[position]
        qImage.setImageResource(questionData.questionImages[position])


        chTrue.isChecked = checkBoxStateA[position]
        chFalse.isChecked = checkBoxStateB[position]

        if (ifExamEnded==0){
            chTrue.isChecked = checkBoxStateA[position]
            chTrue.isClickable = true
            chFalse.isChecked = checkBoxStateB[position]
            chFalse.isClickable = true
        }else{
            chTrue.isChecked = checkBoxStateA[position]
            chFalse.isChecked = checkBoxStateB[position]
            chTrue.isClickable = false
            chFalse.isClickable = false
        }


    }

    override fun getItemCount() = 40

    fun countMistakes() : Int{
        for (i in 0..39){
            if (userGivenAnswers[i]!=questionData.questionAnswers[i]){
                mistakes++
            }
        }
        return mistakes
    }
    fun markMistakes(): Array<Int> {

        val markMistakes = arrayOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        )
        for (i in 0..39){
            if (userGivenAnswers[i]!=questionData.questionAnswers[i]){
                markMistakes[i] = 0
            }
        }
        return  markMistakes
    }
}