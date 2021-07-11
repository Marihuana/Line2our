package com.four.hackerton.line2our.feature.common

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.four.hackerton.line2our.R
import kotlinx.android.synthetic.main.layout_custom_check_button.view.*

class CustomCheckButton : FrameLayout {
    var isChecked : Boolean = false
    var isLike : Boolean = false

    lateinit var view : View

    constructor(context : Context) : this(context, null, 0)
    constructor(context: Context, attrs : AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs : AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).run {
            view = this.inflate(R.layout.layout_custom_check_button,this@CustomCheckButton, false)
            addView(view)

            context.obtainStyledAttributes(attrs, R.styleable.CustomCheckButton).run {
                view.tvCount.text = this.getString(R.styleable.CustomCheckButton_text)
                isLike = this.getBoolean(R.styleable.CustomCheckButton_isLike, false)
                setButtonChecked(false)
                setLayerType(LAYER_TYPE_SOFTWARE, null)
                recycle()
            }
        }
    }

    fun setButtonChecked(value : Boolean){
        this.isChecked = value

        if(value)
            this.view.setBackgroundResource(R.drawable.img_rounded_button_primary)
        else
            this.view.setBackgroundResource(R.drawable.img_rounded_button)

        if(isLike){
            if(value) view.ivIcon.setImageResource(R.drawable.ic_recommend_checked)
            else view.ivIcon.setImageResource(R.drawable.ic_recommend_unchecked)
        }else{
            if(value) view.ivIcon.setImageResource(R.drawable.ic_bookmark_checked)
            else view.ivIcon.setImageResource(R.drawable.ic_bookmark_unchecked)
        }
        if(value)
            this.view.tvCount.setTextColor(Color.parseColor("#FFFFFF"))
        else
            this.view.tvCount.setTextColor(Color.parseColor("#93f06f"))
    }

    fun setCount(count : Int){
        this.view.tvCount.text = count.toString()
    }
}