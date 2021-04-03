package com.example.googleanalytics

import ListData
import MyAdapter
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_list_products.*


class Food : AppCompatActivity() {
    lateinit var firebaseAnalytics : FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        trackScreen("Food Screen")
        setTitle("Food")
        setContentView(R.layout.activity_list_products)
        val data = ArrayList<ListData>()
        data.add(ListData("", "Meat", BitmapFactory.decodeResource(resources, R.drawable.food)))
        data.add(ListData("", "Steak",BitmapFactory.decodeResource(resources, R.drawable.food)))
        data.add(ListData("", "Salad",BitmapFactory.decodeResource(resources, R.drawable.food)))
        data.add(ListData("", "burger",BitmapFactory.decodeResource(resources, R.drawable.food)))
        data.add(ListData("", "salad",BitmapFactory.decodeResource(resources, R.drawable.food)))
        data.add(ListData("", "meat",BitmapFactory.decodeResource(resources, R.drawable.food)))

        recyclerViews.layoutManager =GridLayoutManager(this, 2)
        recyclerViews.setHasFixedSize(true)
        val MyAdapter = MyAdapter(this, data!!)
        recyclerViews.setAdapter(MyAdapter)



    }

    fun trackScreen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}