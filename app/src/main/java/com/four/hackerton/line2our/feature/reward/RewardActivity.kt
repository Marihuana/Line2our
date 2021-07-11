package com.four.hackerton.line2our.feature.reward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.databinding.ActivityRewardBinding
import com.four.hackerton.line2our.feature.common.BaseActivity

class RewardActivity : BaseActivity() {
    lateinit var binding : ActivityRewardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anim =  AnimationUtils.loadAnimation(this, R.anim.anim_crown)
        anim.repeatCount = 10000
        anim.repeatMode = Animation.REVERSE
        binding.ivCrown.startAnimation(anim)
    }
}