package com.four.hackerton.line2our.feature.topic

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.databinding.ActivityRewardBinding
import com.four.hackerton.line2our.databinding.ActivityTopicBinding
import com.four.hackerton.line2our.feature.common.AddPlaceDialog
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.feature.common.CustomCheckButton
import com.four.hackerton.line2our.model.network.model.PlaceVO
import com.four.hackerton.line2our.model.network.model.StationVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import kotlinx.android.synthetic.main.activity_topic.*

class TopicActivity : BaseActivity() , PlaceListAdapter.OnItemClickListener{
    lateinit var binding : ActivityTopicBinding

    lateinit var station : StationVO
    lateinit var topic : TopicVO
    var category : Int = 0

    private val viewModel : TopicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        station = intent.getSerializableExtra("station") as StationVO
        topic = intent.getSerializableExtra("topic") as TopicVO
        category = intent.getIntExtra("category", 0)

        binding.topic = topic
        binding.station = station

        if(category == 0) {
            Toast.makeText(baseContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        init()
        binding()
    }

    override fun onResume() {
        super.onResume()
        dataBinding()
    }

    fun init(){
        binding.btnRecommend.setOnClickListener {
            AddPlaceDialog(this, station, topic).show()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModel.adapter
            viewModel.adapter.listener = this@TopicActivity
        }
    }

    fun binding(){
        viewModel.places.observe(this){
            viewModel.adapter.items.clear()
            viewModel.adapter.items.addAll(it)
            viewModel.adapter.notifyDataSetChanged()
        }
    }

    fun dataBinding(){
        viewModel.getPlaceList(station.id, topic.id)

    }

    override fun onItemClicked(place: PlaceVO) {
        place.url.let {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)));
            }catch (e : Exception){
                Toast.makeText(this, "올바르지 않은 url 입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRecommendButtonClicked(view: CustomCheckButton, place: PlaceVO) {

    }

    override fun onBookMarkButtonClicked(view: CustomCheckButton, place: PlaceVO) {

    }
}