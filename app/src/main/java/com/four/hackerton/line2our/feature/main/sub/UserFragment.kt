package com.four.hackerton.line2our.feature.main.sub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.common.CustomCheckButton
import com.four.hackerton.line2our.feature.reward.RewardActivity
import com.four.hackerton.line2our.feature.station.StationTopicAdapter
import com.four.hackerton.line2our.feature.topic.PlaceListAdapter
import com.four.hackerton.line2our.model.network.model.PlaceVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.view.*

object UserFragment : Fragment() , PlaceListAdapter.OnItemClickListener, StationTopicAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val viewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        init(view)

        return view
    }

    private fun init(view : View){
        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModel.placesAdapter
            viewModel.getBookMarkList()
        }

        view.tabs.addTab(view.tabs.newTab().setText("저장한 장소"))
        view.tabs.addTab(view.tabs.newTab().setText("등록한 장소"))
        view.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    view.recyclerView.apply {
                        adapter = null
                        layoutManager = LinearLayoutManager(context)
                        adapter = if(it == 0){
                            viewModel.getBookMarkList()
                            viewModel.placesAdapter
                        }
                        else{
                            viewModel.getAddedTopic()
                            viewModel.topicsAdapter
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        view.loReward.setOnClickListener{
            startActivity(Intent(context, RewardActivity::class.java))
        }
    }

    override fun onItemClicked(place: PlaceVO) {

    }

    override fun onRecommendButtonClicked(view: CustomCheckButton, place: PlaceVO) {

    }

    override fun onBookMarkButtonClicked(view: CustomCheckButton, place: PlaceVO) {

    }

    override fun onItemClicked(topic: TopicVO) {
        viewModel.getAddedTopic()
    }


}