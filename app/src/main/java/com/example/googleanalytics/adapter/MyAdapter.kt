import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.googleanalytics.Details
import com.example.googleanalytics.Electronic
import com.example.googleanalytics.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.items.view.*
import java.time.Duration
import java.time.LocalDateTime


class MyAdapter(var activity: Activity, val listData: List<ListData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    lateinit var firebaseAnalytics : FirebaseAnalytics
    @RequiresApi(Build.VERSION_CODES.O)
    val currentDateTime = LocalDateTime.now()
    val db = FirebaseFirestore.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity)
        holder.txtid.text = listData[position].ids.toString()
        holder.txtname.text = listData[position].name
        holder.img.setImageBitmap(listData[position].imageDrawable)
        holder.itemView.setOnClickListener(View.OnClickListener() {
//                Toast.makeText(activity, "you Clicked : " + listData[position].imageDrawable, Toast.LENGTH_SHORT).show()
            val endDateTime = LocalDateTime.now()
            val dur: Duration = Duration.between(currentDateTime,endDateTime )
            String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds)
            Log.e("m","Hiiiiiiiiiiiii"+ String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds).toString())
            var map = mutableMapOf("Recycle view Time" to String.format("%02d:%02d:%02d",dur.toHours(), dur.toMinutes(), dur.seconds), "id" to 1)
            db.collection("SpendTime")
                .add(map)
                .addOnSuccessListener {
                    Log.e("m","Hiiiiiiiiiiiii"+ "Done")
                }
                .addOnFailureListener {
                    Log.e("m","Hiiiiiiiiiiiii"+ "Fail")
                }
            xEvent("1","Pressed on : "+ listData[position].name)
            var intent = Intent(activity, Details::class.java)
            intent.putExtra("title",listData[position].name)
            intent.putExtra("image",listData[position].imageDrawable.toString())
            intent.putExtra("details", listData[position].ids)
            activity.startActivity(intent)

        })
    }

    override fun getItemCount(): Int {
        return listData.size
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val txtid =itemView.tvId
         val txtname = itemView.tvName
         val img = itemView.imageCard

    }

        fun xEvent(id: String, name: String){
        val xEventBundle = Bundle()
        xEventBundle.putString("id", id)
        xEventBundle.putString("name", name)
        firebaseAnalytics.logEvent("xEvent", xEventBundle)
    }
}