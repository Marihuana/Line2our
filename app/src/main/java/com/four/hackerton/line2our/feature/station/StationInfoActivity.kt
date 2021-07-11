package com.four.hackerton.line2our.feature.station

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.databinding.ActivityRewardBinding
import com.four.hackerton.line2our.databinding.ActivityStationInfoBinding
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.feature.topic.TopicActivity
import com.four.hackerton.line2our.model.network.model.StationVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import kotlinx.android.synthetic.main.activity_station_info.*

class StationInfoActivity : BaseActivity(), StationInfoViewModel.StationInfoHandler, StationTopicAdapter.OnItemClickListener {
    lateinit var binding : ActivityStationInfoBinding

    private val viewModel : StationInfoViewModel by viewModels()
    var stationId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        bindData()
    }

    private fun init(){
        stationId = intent.getIntExtra("stationId", 0)
        if(stationId == 0){
            Toast.makeText(applicationContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.onSelectedCategoryListener = this
        binding.catRestaurant.isSelected = true
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            viewModel.adapter.listener = this@StationInfoActivity
            this.adapter = viewModel.adapter
        }

        catRestaurant.setOnClickListener {
            selectedCategory(1)
            viewModel.getTopics(stationId, 1)
        }
        catCafe.setOnClickListener {
            selectedCategory(2)
            viewModel.getTopics(stationId, 2)
        }
        catCulture.setOnClickListener {
            selectedCategory(3)
            viewModel.getTopics(stationId, 3)
        }
        catShop.setOnClickListener {
            selectedCategory(4)
            viewModel.getTopics(stationId, 4)
        }

        tvPrev.setOnClickListener {
            viewModel.station.value?.let {
                startActivity(Intent(this, StationInfoActivity::class.java).apply { putExtra("stationId", it.prevId) })
                finish()
            }
        }

        tvNext.setOnClickListener {
            viewModel.station.value?.let {
                startActivity(Intent(this, StationInfoActivity::class.java).apply { putExtra("stationId", it.nextId) })
                finish()
            }
        }
    }

    private fun bindData(){
        viewModel.getStationInfo(stationId)
        viewModel.getTopics(stationId, 1)

        viewModel.station.observe(this){
            binding.station = it
        }

        viewModel.badge.observe(this){
            if(it) binding.ivReward.visibility = View.VISIBLE
            else binding.ivReward.visibility = View.GONE
        }

        viewModel.topics.observe(this){
            viewModel.adapter.items.clear()
            viewModel.adapter.items.addAll(it)
            viewModel.adapter.notifyDataSetChanged()
        }
    }

    private fun selectedCategory(categoryId : Int){
        when(categoryId){
            1 -> {
                binding.catRestaurant.isSelected = true
                binding.catCafe.isSelected = false
                binding.catCulture.isSelected = false
                binding.catShop.isSelected = false
            }
            2 -> {
                binding.catRestaurant.isSelected = false
                binding.catCafe.isSelected = true
                binding.catCulture.isSelected = false
                binding.catShop.isSelected = false
            }
            3 -> {
                binding.catRestaurant.isSelected = false
                binding.catCafe.isSelected = false
                binding.catCulture.isSelected = true
                binding.catShop.isSelected = false
            }
            4 -> {
                binding.catRestaurant.isSelected = false
                binding.catCafe.isSelected = false
                binding.catCulture.isSelected = false
                binding.catShop.isSelected = true
            }
            else -> return
        }
    }

    override fun onSelectedCategory(catId: Int) {
        selectedCategory(catId)
    }

    override fun nextStation() {
        viewModel.station.value?.let {
            startActivity(Intent(this, StationInfoActivity::class.java).apply { putExtra("stationId", it.nextId) })
            finish()
        }
    }

    override fun prevStation() {
        viewModel.station.value?.let {
            startActivity(Intent(this, StationInfoActivity::class.java).apply { putExtra("stationId", it.prevId) })
            finish()
        }
    }

    override fun onItemClicked(topic: TopicVO) {
        startActivity(Intent(this, TopicActivity::class.java).apply {
            putExtra("station", viewModel.station.value)
            putExtra("topic", topic)
            putExtra("category", viewModel.activateCategory)
        })
    }
}