package com.four.hackerton.line2our.feature.main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.databinding.ActivityMainBinding
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.feature.main.sub.HomeFragment
import com.four.hackerton.line2our.feature.main.sub.UserFragment
import com.four.hackerton.line2our.model.network.model.StationVO
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.name
    lateinit var binding : ActivityMainBinding

    val viewModel : MainViewModel by viewModels()

    var activeFragment : Fragment = HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()
        bind()
        bindData()
    }
    private fun init(){
        supportFragmentManager.beginTransaction().apply {
            this.replace(container.id, HomeFragment)
            this.commitAllowingStateLoss()
        }

        bnvMain.setOnItemSelectedListener{ item ->
            changeTab(item.itemId)
            true
        }
    }

    private fun bind(){
        viewModel.weeklyTopic.observe(this){
            binding.loWeeklyTopic.visibility = View.VISIBLE

            object : CountDownTimer(3000L, 1000L) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    binding.loWeeklyTopic.visibility = View.GONE
                }
            }.start()
        }
    }

    private fun bindData(){
        viewModel.getWeeklyTopic()
    }

    private fun changeTab(id: Int) {
        val selected = getTabFragment(id)

        if(selected == activeFragment) return

        supportFragmentManager.beginTransaction().apply {
            this.replace(container.id, selected)
            this.commit()
            activeFragment = selected
        }
    }

    private fun getTabFragment(id: Int): Fragment {
        return when(id) {
            R.id.nav_home -> HomeFragment
            R.id.nav_user -> UserFragment
            else -> HomeFragment
        }
    }
}