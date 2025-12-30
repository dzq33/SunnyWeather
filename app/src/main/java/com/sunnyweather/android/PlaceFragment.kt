package com.sunnyweather.android

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.ui.place.PlaceAdapter
import com.sunnyweather.android.ui.place.PlaceViewModel

class PlaceFragment: Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }
    private lateinit var adapter: PlaceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManger = LinearLayoutManager(activity)
        val ryView = view.findViewById<RecyclerView>(R.id.recyclerView)
        ryView.layoutManager = layoutManger
        adapter = PlaceAdapter(this,viewModel.placeList)
        ryView.adapter = adapter
        view.findViewById<EditText>(R.id.searchPlaceEdit).addTextChangedListener{
            editable ->
            val content = editable.toString()
            if(!content.isEmpty()){
                viewModel.searchPlace(content)
            }else{
                ryView.visibility = View.GONE
                view.findViewById<ImageView>(R.id.bgImageView).visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(this, Observer{
            result ->
            val place = result.getOrNull()
            if(place != null){
                val ryView = view.findViewById<RecyclerView>(R.id.recyclerView)
                ryView.visibility = View.VISIBLE
                view.findViewById<ImageView>(R.id.bgImageView).visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(place)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(context,"未查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}