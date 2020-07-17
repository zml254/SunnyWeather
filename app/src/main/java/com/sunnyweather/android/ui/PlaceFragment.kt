package com.sunnyweather.android.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.ui.adapter.PlaceAdapter
import com.sunnyweather.android.viewmodel.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = activity?.intent
        var run = intent?.getBooleanExtra("run", false)
        if (run == null) {
            run = false
        }
        if (viewModel.isSavedPlace() && activity is MainActivity && !run) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
//        btn_place_search.setOnClickListener {
//        }
        val layoutManager = LinearLayoutManager(activity)
        rv_place.layoutManager = layoutManager
        adapter = PlaceAdapter(
            this,
            viewModel.placeList
        )
        rv_place.adapter = adapter
        et_place_searchPlace.addTextChangedListener { editable->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                rv_place.visibility = View.GONE
                iv_place_bg.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result->
            val places = result.getOrNull()
            if (places != null) {
                rv_place.visibility = View.VISIBLE
                iv_place_bg.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
                val animator = AnimationUtils.loadLayoutAnimation(
                    SunnyWeatherApplication.context,
                    R.anim.layout_animation_fall_down
                )
                rv_place.layoutAnimation = animator
                rv_place.scheduleLayoutAnimation()
            } else {
                Toast.makeText(activity,"未查询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}