package com.example.googleanalytics

import ListData
import MyAdapter
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_cloths.*
import java.time.LocalDateTime

class Cloths : AppCompatActivity() {
    lateinit var firebaseAnalytics : FirebaseAnalytics
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloths)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        setTitle("Cloths")
        val data = ArrayList<ListData>()
        data.add( ListData("", "Jeans", BitmapFactory.decodeResource(resources, R.drawable.cloths)) )
        data.add( ListData("", "T-shirt",BitmapFactory.decodeResource(resources, R.drawable.cloths)) )
        data.add( ListData("", "Boots",BitmapFactory.decodeResource(resources, R.drawable.cloths)) )
        data.add( ListData("", "Sneakers",BitmapFactory.decodeResource(resources, R.drawable.cloths)) )
        data.add( ListData("", "Jacket",BitmapFactory.decodeResource(resources, R.drawable.cloths)) )
        data.add( ListData("", "Hello",BitmapFactory.decodeResource(resources, R.drawable.cloths)) )

        recyclerViewCloths.layoutManager = GridLayoutManager(this,2)
        recyclerViewCloths.setHasFixedSize(true)
        val MyAdapter = MyAdapter(this, data!!)
        recyclerViewCloths.setAdapter(MyAdapter)
        trackScreen("Cloths Screen")
    }

    fun trackScreen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}