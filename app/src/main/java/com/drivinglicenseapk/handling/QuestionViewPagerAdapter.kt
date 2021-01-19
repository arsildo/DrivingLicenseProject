package com.drivinglicenseapk.handling


import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.drivinglicenseapk.R
import kotlinx.android.synthetic.main.item_question.view.*


class QuestionViewPagerAdapter(private val questionData: QuestionData) :
    RecyclerView.Adapter<QuestionViewPagerAdapter.QuestionViewPagerViewHolder>() {

    private val checkBoxTrueState = SparseBooleanArray(10)
    private val checkBoxFalseState = SparseBooleanArray(10)

    inner class QuestionViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val questionPosition = adapterPosition

        var qString: TextView = itemView.questionString
        var qImage : ImageView = itemView.questionImage
        var qTrue : CheckBox = itemView.trueCheckBox
        var qFalse : CheckBox = itemView.falseCheckBox

        init {

            qTrue.setOnClickListener{
                qFalse.isChecked = false
                if(!checkBoxTrueState.get(adapterPosition,false)){
                    qTrue.isChecked = true
                    checkBoxTrueState.put(adapterPosition,true)
                    checkBoxFalseState.put(adapterPosition,false)
                }else{
                    qTrue.isChecked = false
                    checkBoxTrueState.put(adapterPosition,false)
                    checkBoxFalseState.put(adapterPosition,true)
                }
            }

            qFalse.setOnClickListener{
                qTrue.isChecked = false
                if(!checkBoxFalseState.get(adapterPosition,false)){
                    qFalse.isChecked = true
                    checkBoxFalseState.put(adapterPosition,true)
                    checkBoxTrueState.put(adapterPosition,false)
                }else{
                    qFalse.isChecked = false
                    checkBoxFalseState.put(adapterPosition,false)
                    checkBoxTrueState.put(adapterPosition,true)
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
        val qTrue = holder.qTrue
        val qFalse = holder.qFalse


        qString.text = questionData.questionStrings[position]
        qImage.setImageResource(questionData.questionImages[position])


        qTrue.isChecked = checkBoxTrueState.get(position,false)
        qFalse.isChecked = checkBoxFalseState.get(position,false)




    }

    override fun getItemCount() = 10

}