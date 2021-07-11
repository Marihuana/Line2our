package com.four.hackerton.line2our.feature.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.databinding.ItemAddPlaceBinding
import com.four.hackerton.line2our.model.network.model.Place
import com.four.hackerton.line2our.model.network.model.StationVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import com.four.hackerton.line2our.model.network.repository.KakaoRepository
import com.four.hackerton.line2our.model.network.repository.TopicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_add.*

class AddPlaceDialog(context: Context, val station: StationVO, val topic: TopicVO) : Dialog(context) {
    val repository = KakaoRepository()
    var places = MutableLiveData<List<Place>>()
    var adapter = AddPlaceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setLayout()
    }

    private fun setLayout() {
        setContentView(R.layout.dialog_add)

        val lpWindow = WindowManager.LayoutParams().apply {
            flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            dimAmount = 0.6f
        }

        window?.apply {
            attributes = lpWindow
            setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        init()
    }

    private fun init(){
        tvPlace.text = station.name
        tvTopic.text = topic.title
        tvDesc.text = "12명이 참여했어요"
        etSearch.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(loResult.visibility == View.VISIBLE) loResult.visibility = View.GONE

                val keyword = s.toString()
                if(count > 0 && before < count){
                    getPlaceList(keyword)
                }else if(count <= 0){
                    adapter.items.clear()
                    adapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        btnDelete.setOnClickListener {
            if(etSearch.text.isNotEmpty()) etSearch.setText("")
        }

        btnResult.setOnClickListener{
            dismiss()
        }

        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = this@AddPlaceDialog.adapter.apply {
                listener = object : AddPlaceAdapter.OnItemClickListener {
                    override fun onItemClicked(place: Place) {
                        addPlace(place)
                    }
                }
            }
        }
    }

    private fun addPlace(place : Place){
        TopicRepository.setPlace(topic.id, place)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                etSearch.setText("")
                adapter.items.clear()
                adapter.notifyDataSetChanged()

                loResult.visibility = View.VISIBLE

                if(response.result == "0000"){
                    tvResult.setText("새로운 장소 발견, 등록 완료!")
                    ivResult.setImageResource(R.drawable.img_result_success)
                }else{
                    tvResult.setText("앗! 다른 사람이 이미 등록한 곳이에요")
                    ivResult.setImageResource(R.drawable.img_result_fail)
                }
            }
            .doOnError {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
            .subscribe()
    }

    private fun getPlaceList(keyword : String){
        KakaoRepository.ParametersBuilder().apply {
            this.keyword = keyword
            this.radius = 1000
            this.x = station.latitude.toString()
            this.y = station.longitude.toString()
        }.build().let {
            repository.searchPlace(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess { response ->
                    places.value = response.places
                    adapter.items = response.places
                    adapter.notifyDataSetChanged()
                }
                .doOnError {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                .subscribe()
        }
    }
}

class AddPlaceAdapter() : RecyclerView.Adapter<AddPlaceAdapter.ViewHolder>() {
    var items = ArrayList<Place>()
    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAddPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding : ItemAddPlaceBinding): RecyclerView.ViewHolder(binding.root){
        fun bindViewHolder(place: Place){
            binding.item = place

            binding.root.setOnClickListener {
                listener?.onItemClicked(place)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(place: Place)
    }
}