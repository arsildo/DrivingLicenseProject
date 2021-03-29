package com.drivinglicenseapk.handling


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import com.drivinglicenseapk.handling.data.QuestionAnswers
import com.drivinglicenseapk.handling.data.QuestionImages
import com.drivinglicenseapk.handling.data.QuestionStrings
import kotlinx.android.synthetic.main.item_question.view.*
import java.util.*


class QuestionAdapter(
    private val questionAnswers: QuestionAnswers,
    private val questionStrings: QuestionStrings,
    private val questionImages: QuestionImages
) :
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
    var examEditState : Boolean = false

    private var generatedOnce = false
    private val randomIndexes = arrayOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    )

    inner class QuestionViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var qString: TextView = itemView.questionString
        var qImage : ImageView = itemView.questionImage
        var chTrue : CheckBox = itemView.trueCheckBox
        var chFalse : CheckBox = itemView.falseCheckBox
        var wrongMark : ImageView = itemView.wrongMark

        init {
            if (!examEditState) {
                chTrue.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        chFalse.isChecked = false
                        checkBoxStateA[adapterPosition] = true
                        userGivenAnswers[adapterPosition] = "Sakte"
                    } else {
                        checkBoxStateA[adapterPosition] = false
                    }
                }

                chFalse.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        chTrue.isChecked = false
                        checkBoxStateB[adapterPosition] = true
                        userGivenAnswers[adapterPosition] = "Gabim"
                    } else {
                        checkBoxStateB[adapterPosition] = false
                    }
                }

            }else{
                chTrue.isClickable = false
                chFalse.isClickable = false
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
        val wrongMark = holder.wrongMark
        generateIndexes()
        qString.text = questionStrings.questionStrings[randomIndexes[position]]
        qImage.setImageResource(questionImages.questionImages[randomIndexes[position]])

        Log.d("MATCH NR STRING","${questionStrings.questionStrings.size}")
        Log.d("MATCH NR IMAGE","${questionImages.questionImages.size}")
        Log.d("MATCH  NR ANSWER","${questionAnswers.questionAnswers.size}")
        wrongMark.isVisible = false


        val markMistakes = markMistakes()
        if (!examEditState){
            chTrue.isChecked = checkBoxStateA[position]
            chFalse.isChecked = checkBoxStateB[position]
        }else{
            chTrue.isChecked = checkBoxStateA[position]
            chFalse.isChecked = checkBoxStateB[position]
            chTrue.isClickable = false
            chFalse.isClickable = false
            wrongMark.isVisible = true
            if (markMistakes[position]!=0){
                wrongMark.setImageResource(R.drawable.ic_correct)
            }else{
                wrongMark.setImageResource(R.drawable.ic_wrong)
            }

        }

    }

    override fun getItemCount() = 40

    // TODO NEW GENERATING INDEX LOGIC
    private fun generateIndexes(): Array<Int> {
        if (!generatedOnce){
            for (i in 0..39){
                val random = Random().nextInt(questionAnswers.questionAnswers.size)
                randomIndexes[i] = random
                if (randomIndexes.contains(random)) randomIndexes[i]=Random().nextInt(questionAnswers.questionAnswers.size)
            }
            generatedOnce = true
        }
        return randomIndexes
    }

    fun countMistakes() : Int{
        mistakes=0
        for (i in 0..39){
            if (userGivenAnswers[i]!=questionAnswers.questionAnswers[generateIndexes()[i]]){
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
            if (userGivenAnswers[i]!=questionAnswers.questionAnswers[generateIndexes()[i]]){
                markMistakes[i] = 0
            }
        }
        return  markMistakes
    }
}