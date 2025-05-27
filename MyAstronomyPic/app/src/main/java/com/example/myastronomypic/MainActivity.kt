package com.example.myastronomypic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), APoDListener {
    lateinit var fetcher : APoDFetcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val errorMsg = findViewById<TextView>(R.id.errorMsg)
        errorMsg.text = ""

        fetcher = APoDFetcher(this, this)
    }


    //API: https://data.nasa.gov/Space-Science/Astronomy-Picture-of-the-Day-API/ez2w-t8ua/about_data


    fun doRequest(view: View) {
        val dateInput = findViewById<EditText>(R.id.TimeInput)

        fetcher.doRequest(dateInput)
    }

    override fun processAPoDResult(apod: APoDResult) {
        val imgTitle = findViewById<TextView>(R.id.imgTitle)
        val copyright = findViewById<TextView>(R.id.copyright)
        val description = findViewById<TextView>(R.id.description)
        val imgView = findViewById<ImageView>(R.id.imgView)
        val errorMsg = findViewById<TextView>(R.id.errorMsg)


        imgTitle.text = apod.imgTitle
        copyright.text = apod.copyright
        description.text = apod.description
        errorMsg.text = ""


        //Image loader
        //Resource: https://www.geeksforgeeks.org/how-to-load-any-image-from-url-without-using-any-dependency-in-android/
        val exe = Executors.newSingleThreadExecutor()
        val handle = Handler(Looper.getMainLooper())
        var img: Bitmap? = null

        exe.execute {
            val imgURL = apod.imgView

            try {
                val `in` = java.net.URL(imgURL).openStream()
                img = BitmapFactory.decodeStream(`in`)

                handle.post {
                    imgView.setImageBitmap(img)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //Other resources found along the way:
        //https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
        //https://www.youtube.com/watch?v=8BHDAg9igl0

    }

    override fun processAPoDError(apod: APoDError) {
        val imgTitle = findViewById<TextView>(R.id.imgTitle)
        val copyright = findViewById<TextView>(R.id.copyright)
        val description = findViewById<TextView>(R.id.description)
        val imgView = findViewById<ImageView>(R.id.imgView)
        val errorMsg = findViewById<TextView>(R.id.errorMsg)


        imgTitle.text = ""
        copyright.text = ""
        description.text = apod.msg
        errorMsg.text = "Data load fail"

        val exe = Executors.newSingleThreadExecutor()
        val handle = Handler(Looper.getMainLooper())
        var img: Bitmap? = null

        exe.execute {
            val imgURL = "https://developers.google.com/static/maps/documentation/maps-static/images/error-image-generic.png"

            try {
                val `in` = java.net.URL(imgURL).openStream()
                img = BitmapFactory.decodeStream(`in`)

                handle.post {
                    imgView.setImageBitmap(img)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}