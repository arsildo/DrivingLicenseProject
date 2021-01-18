package com.drivinglicenseapk.handling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.activity_exam.view.*
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionViewPagerAdapter(private val questionData: QuestionData) :
    RecyclerView.Adapter<QuestionViewPagerAdapter.QuestionViewPagerViewHolder>() {

    inner class QuestionViewPagerViewHolder(questionView : View) : RecyclerView.ViewHolder(questionView){
        val questionString = questionView.questionString
        val questionImage = questionView.questionImage
        val nextQuestion = questionView.nextQuestion

        val questionPosition = adapterPosition



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewPagerViewHolder {
        return QuestionViewPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_question,parent,false))
    }

    override fun onBindViewHolder(holder: QuestionViewPagerViewHolder, position: Int) {
        val currentQuestion = questionData.questionStrings[position]

        holder.itemView.questionString.text = questionData.questionStrings.random()
        holder.itemView.questionImage.setImageResource(questionData.questionImages.random())

        holder.itemView.falseCheckBox.setOnClickListener {
            holder.itemView.trueCheckBox.isChecked = false
        }

        holder.itemView.trueCheckBox.setOnClickListener {
            holder.itemView.falseCheckBox.isChecked = false
        }








    }

    override fun getItemCount() = 10

}