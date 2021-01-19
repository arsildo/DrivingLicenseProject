package com.drivinglicenseapk.handling


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.item_question.view.*


class QuestionViewPagerAdapter(private val questionData: QuestionData) :
    RecyclerView.Adapter<QuestionViewPagerAdapter.QuestionViewPagerViewHolder>() {

    inner class QuestionViewPagerViewHolder(questionView: View) : RecyclerView.ViewHolder(questionView){

        val questionPosition = adapterPosition

        var qString: TextView = questionView.questionString
        var qImage : ImageView = questionView.questionImage
        var qTrue : CheckBox = questionView.trueCheckBox
        var qFalse : CheckBox = questionView.falseCheckBox

        init {

        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewPagerViewHolder {
        return QuestionViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }


    override fun onBindViewHolder(holder: QuestionViewPagerViewHolder, position: Int) {


        val currentQuestion = questionData.questionStrings[position]


        val qString = holder.qString
        val qImage = holder.qImage
        val qTrue = holder.qTrue
        val qFalse = holder.qFalse


        qString.text = questionData.questionStrings[position]
        qImage.setImageResource(questionData.questionImages[position])




    }

    override fun getItemCount() = questionData.questionStrings.size

}