package com.example.googleanalytics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_details.*
import java.time.Duration
import java.time.LocalDateTime

class Details : AppCompatActivity() {
    lateinit var firebaseAnalytics : FirebaseAnalytics
    val db = FirebaseFirestore.getInstance()
    @RequiresApi(Build.VERSION_CODES.O)
    val currentDateTime = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        getIncomingIntent()

        trackScreen("Details Screen")

    }
    fun getIncomingIntent(){
        if(getIntent().hasExtra("title")&& getIntent().hasExtra("image")){
//            var imgUrl: Bitmap? = BitmapFactory.decodeByteArray( getIntent().getByteArrayExtra("image"), getIntent().getByteArrayExtra("image")!!.lastIndex)
            var name: String? = getIntent().getStringExtra("title")
            var details: String? = getIntent().getStringExtra("details")

            setImage(BitmapFactory.decodeResource(resources, R.drawable.cloths),name!!, details!!)

        }
    }

    fun setImage(image: Bitmap, name:String, details:String){
        textView.setText(name)
        imgDetails.setImageBitmap(image)
        textView3.setText(details)
    }

    fun trackScreen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStop() {
        super.onStop()
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
    }
}