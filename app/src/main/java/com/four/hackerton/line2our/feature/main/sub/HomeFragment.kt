package com.four.hackerton.line2our.feature.main.sub

import android.graphics.PointF
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.scaleMatrix
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.four.hackerton.line2our.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

object HomeFragment : Fragment() {
    val stations = mapOf(
        Pair("합정", Rect(787,683,882,779)),
        Pair("홍대", Rect(938,679,1042,783)),
        Pair("신촌", Rect(1101,674,1201,785)),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        return view
    }

    fun init(view : View){
        view.subwayLine.apply {
            setImage(ImageSource.asset("line2.png"))
            maxScale = 0.75F
            isClickable = true
            setOnStateChangedListener(object : SubsamplingScaleImageView.OnStateChangedListener {
                override fun onScaleChanged(newScale: Float, origin: Int) {
                    Log.d("onScaleChanged", "newScale : $newScale, origin : $origin")
                }

                override fun onCenterChanged(newCenter: PointF?, origin: Int) {
                    Log.d("onCenterChanged", "newCenter : $newCenter, origin : $origin")
                }

            })

            setOnTouchListener{view, event ->
                val x = event.getX()
                val y = event.getY()

                var bound = viewToSourceCoord(x,y)
                for(station in stations){
                    if(station.value.contains(bound?.x?.toInt() ?: 0, bound?.y?.toInt() ?: 0)){
                        Log.d("click", "메뉴 펼침 ${station.key}")
                    }
                }
                Log.d("touch", "X : ${bound?.x}, Y : $${bound?.y}")

                false
            }

        }


    }

    fun test(){


    }

}