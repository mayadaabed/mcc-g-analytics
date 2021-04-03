package com.example.googleanalytics

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.items.*
import java.sql.Time
import java.time.Duration
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAnalytics : FirebaseAnalytics
    val db = FirebaseFirestore.getInstance()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        val currentDateTime = LocalDateTime.now()
    firebaseAnalytics = FirebaseAnalytics.getInstance(this)


        food_btn.setOnClickListener {
            val endDateTime = LocalDateTime.now()
            val dur: Duration = Duration.between(currentDateTime,endDateTime )
            String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds)
            Log.e("m","Hiiiiiiiiiiiii"+ String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds).toString())
            var map = mutableMapOf("mainActivityTime" to String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds), "id" to 1)
            db.collection("SpendTime")
                .add(map)
                .addOnSuccessListener {
                    Log.e("m","Hiiiiiiiiiiiii"+ "Done")
                }
                .addOnFailureListener {
                    Log.e("m","Hiiiiiiiiiiiii"+ "Fail")
                }
            selectContent("1", "Me", "Food Button")
            xEvent("1", "Food")
            var intent = Intent(this, Food::class.java)
            startActivity(intent)

        }

    cloth_btn.setOnClickListener {
        val endDateTime = LocalDateTime.now()
        val dur: Duration = Duration.between(currentDateTime,endDateTime )
        String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds)
        Log.e("m","Hiiiiiiiiiiiii"+ String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds).toString())
        var map = mutableMapOf("mainActivityTime" to String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds), "id" to 1)
        db.collection("SpendTime")
            .add(map)
            .addOnSuccessListener {
                Log.e("m","Hiiiiiiiiiiiii"+ "Done")
            }
            .addOnFailureListener {
                Log.e("m","Hiiiiiiiiiiiii"+ "Fail")
            }
        selectContent("1", "Me", "Cloths Button")
        xEvent("1", "Cloths")
        var intent = Intent(this, Cloths::class.java)
        startActivity(intent)
    }

    elec_btn.setOnClickListener {
        val endDateTime = LocalDateTime.now()
        val dur: Duration = Duration.between(currentDateTime,endDateTime )
        String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds)
        Log.e("m","Hiiiiiiiiiiiii"+ String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds).toString())
        var map = mutableMapOf("mainActivityTime" to String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds), "id" to 1)
        db.collection("SpendTime")
            .add(map)
            .addOnSuccessListener {
                Log.e("m","Hiiiiiiiiiiiii"+ "Done")
            }
            .addOnFailureListener {
                Log.e("m","Hiiiiiiiiiiiii"+ "Fail")
            }
        selectContent("1", "Me", "Electronic Button")
        xEvent("1", "Electronic")
        var intent = Intent(this, Electronic::class.java)
        startActivity(intent)
    }


        trackScreen("Main screen")
    }

    fun selectContent(id: String, name: String, contentType: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun xEvent(id: String, name: String){
        val xEventBundle = Bundle()
        xEventBundle.putString("id", id)
        xEventBundle.putString("name", name)
        firebaseAnalytics.logEvent("xEvent", xEventBundle)
    }

    fun trackScreen(screenName: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

}