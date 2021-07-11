package com.four.hackerton.line2our.feature.main.sub

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PointF
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.main.MainActivity
import com.four.hackerton.line2our.feature.station.StationInfoActivity
import com.four.hackerton.line2our.feature.topic.TopicViewModel
import com.four.hackerton.line2our.model.network.model.StationVO
import kotlinx.android.synthetic.main.fragment_home.view.*

object HomeFragment : Fragment() {
    val stations = mapOf(
        Pair(1, Rect(1700,3020,1956,3270)),
        Pair(2, Rect(1840,2652,2096,2900)),
        Pair(3, Rect(2140,2396,2396,2650)),
        Pair(4, Rect(2600,2248,2856,2500)),
        Pair(5, Rect(3092,2252,3348,2500)),
        Pair(6, Rect(3576,2248,3832,2500)),
        Pair(7, Rect(4068,2248,4324,2500)),
        Pair(8, Rect(4548,2248,4804,2500)),
        Pair(9, Rect(5032,2248,5288,2500)),
        Pair(10, Rect(5516,2248,5772,2500)),
        Pair(11, Rect(5996,2244,6252,2500)),
        Pair(12, Rect(6484,2248,6740,2500)),
        Pair(13, Rect(6928,2356,7184,2610)),
        Pair(14, Rect(7160,2664,7416,2920)),
        Pair(15, Rect(7380,3188,7636,3448)),
        Pair(16, Rect(7380,3680,7636,3940)),
        Pair(17, Rect(7380,4172,7636,4432)),
        Pair(18, Rect(7380,4648,7636,4908)),
        Pair(19, Rect(7384,5140,7640,5400)),
        Pair(20, Rect(7380,5616,7636,5876)),
        Pair(21, Rect(7380,6104,7636,6364)),
        Pair(22, Rect(7356,6512,7612,6772)),
        Pair(23, Rect(7144,6888,7400,7148)),
        Pair(24, Rect(6752,7128,7008,7388)),
        Pair(25, Rect(6344,7168,6600,7428)),
        Pair(26, Rect(5904,7168,6160,7428)),
        Pair(27, Rect(5464,7168,5720,7428)),
        Pair(28, Rect(5024,7164,5280,7424)),
        Pair(29, Rect(4580,7168,4836,7428)),
        Pair(30, Rect(4136,7164,4392,7424)),
        Pair(31, Rect(3692,7168,3948,7428)),
        Pair(32, Rect(3256,7168,3512,7428)),
        Pair(33, Rect(2820,7168,3076,7428)),
        Pair(34, Rect(2372,7148,2628,7408)),
        Pair(35, Rect(2028,6980,2284,7240)),
        Pair(36, Rect(1776,6632,2032,6892)),
        Pair(37, Rect(1692,6128,1948,6388)),
        Pair(38, Rect(1700,5676,1956,5936)),
        Pair(39, Rect(1692,5232,1948,5492)),
        Pair(40, Rect(1692,4796,1948,5056)),
        Pair(41, Rect(1692,4352,1948,4612)),
        Pair(42, Rect(1688,3900,1944,4160)),
        Pair(43, Rect(1692,3480,1948,3740)),
        Pair(44, Rect(6532,1512,6788,1772)),
        Pair(45, Rect(7040,1516,7296,1776)),
        Pair(46, Rect(7384,1860,7640,2120)),
        Pair(47, Rect(7464,2460,7720,2710)),
        Pair(48, Rect(548,5184,804,5444)),
        Pair(49, Rect(640,5540,896,5800)),
        Pair(50, Rect(984,5676,1240,5936)),
        Pair(51, Rect(1344,5676,1600,5936))
    )
    private val viewModel : HomeViewModel by viewModels()

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

    @SuppressLint("ClickableViewAccessibility")
    fun init(view : View){
        view.subwayLine.apply {
            setImage(ImageSource.asset("home.png"))
            setScaleAndCenter(0.25F, PointF(2157.7231F, 4274.5117F))
            maxScale = 0.75F
            isClickable = true

            val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener(){
                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    val bound = viewToSourceCoord(e?.x ?: 0F,e?.y ?: 0F)
                    for(station in stations){
                        if(station.value.contains(bound?.x?.toInt() ?: 0, bound?.y?.toInt() ?: 0)){
                            showStationDetail(station.key)
                            return true
                        }
                    }
                    return false
                }
            })

            setOnTouchListener{_, event ->
                gestureDetector.onTouchEvent(event)
                false
            }
        }
    }

    fun showStationDetail(stationId: Int){
        startActivity(Intent(context, StationInfoActivity::class.java).apply { putExtra("stationId", stationId) })
    }
}