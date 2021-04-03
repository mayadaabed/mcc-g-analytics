package com.example.googleanalytics

import ListData
import MyAdapter
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_electronic.*
import kotlinx.android.synthetic.main.activity_list_products.*
import kotlinx.android.synthetic.main.activity_list_products.recyclerViews
import kotlinx.android.synthetic.main.items.*

class Electronic : AppCompatActivity() {
    lateinit var firebaseAnalytics : FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setTitle("Electronic")
        setContentView(R.layout.activity_electronic)
        val data = ArrayList<ListData>()
        data.add( ListData("", "Iphone X", BitmapFactory.decodeResource(resources, R.drawable.phones)) )
        data.add( ListData("", "Laptop Hp", BitmapFactory.decodeResource(resources, R.drawable.phones)) )
        data.add( ListData("", "A51", BitmapFactory.decodeResource(resources, R.drawable.phones)) )
        data.add( ListData("", "S20 ultra", BitmapFactory.decodeResource(resources, R.drawable.phones)) )
        data.add( ListData("", "Iphone 12", BitmapFactory.decodeResource(resources, R.drawable.phones)) )
        data.add( ListData("", "A71", BitmapFactory.decodeResource(resources, R.drawable.phones)) )

        recyclerViewElectronic.layoutManager = GridLayoutManager(this,2)
        recyclerViewElectronic.setHasFixedSize(true)
        val MyAdapter = MyAdapter(this, data!!)
        recyclerViewElectronic.setAdapter(MyAdapter)
        trackScreen("Eletronic Screen")


    }

    fun trackScreen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}