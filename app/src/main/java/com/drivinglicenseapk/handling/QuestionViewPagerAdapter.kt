package com.drivinglicenseapk.handling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionViewPagerAdapter(private val questionData: QuestionData) :
    RecyclerView.Adapter<QuestionViewPagerAdapter.QuestionViewPagerViewHolder>() {

    inner class QuestionViewPagerViewHolder(questionView : View) : RecyclerView.ViewHolder(questionView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewPagerViewHolder {
        return QuestionViewPagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_question,parent,false))
    }

    override fun onBindViewHolder(holder: QuestionViewPagerViewHolder, position: Int) {
        val currentQuestion = questionData.questionStrings[position]
        holder.itemView.questionString.text = questionData.questionStrings.random()
        holder.itemView.questionImage.setImageResource(questionData.questionImages.random())
    }

    override fun getItemCount() = 4


}