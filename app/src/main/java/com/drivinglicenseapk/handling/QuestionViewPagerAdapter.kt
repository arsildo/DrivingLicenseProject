package com.drivinglicenseapk.handling


import android.util.SparseBooleanArray
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

    private val checkBoxStateA = SparseBooleanArray(10)
    private val checkBoxStateB = SparseBooleanArray(10)

    inner class QuestionViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var qString: TextView = itemView.questionString
        var qImage : ImageView = itemView.questionImage
        var chTrue : CheckBox = itemView.trueCheckBox
        var chFalse : CheckBox = itemView.falseCheckBox

        init {

            chTrue.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    chFalse.isChecked = false
                    checkBoxStateA.put(adapterPosition, true)
                }else {
                    checkBoxStateA.put(adapterPosition,false)
                }
            }
            chFalse.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    chTrue.isChecked = false
                    checkBoxStateB.put(adapterPosition, true)
                }else{
                    checkBoxStateB.put(adapterPosition,false)
                }
            }

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
        val chTrue = holder.chTrue
        val chFalse = holder.chFalse

        qString.text = questionData.questionStrings[position]
        qImage.setImageResource(questionData.questionImages[position])

        chTrue.isChecked = checkBoxStateA.get(position,false)
        chFalse.isChecked = checkBoxStateB.get(position,false)

    }

    override fun getItemCount() = 10

}