package com.mirkamol.handmadelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var laptops: ArrayList<Laptop>
    private lateinit var loadButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        laptops = ArrayList()

        for (i in 0..2) {
            laptops.add(
                Laptop(
                    R.drawable.img,
                    "laptop",
                    "Laptop Laptop Laptop Laptop"
                )
            )
        }

        initViews()

        if (savedInstanceState !=null){
           val savedLaptops =  savedInstanceState.getParcelableArrayList<Laptop>("laptops")
            savedLaptops!!.forEach {
                linearLayout.addView(addItem(it))
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList("laptops", laptops)
    }


    private fun initViews() {
        linearLayout = findViewById(R.id.container_root)
        loadButton = findViewById(R.id.btn_load)

        loadButton.setOnClickListener {
            for (laptop in laptops) {
                linearLayout.addView(addItem((laptop)))
            }

        }


    }

    private fun addItem(laptop: Laptop): LinearLayout {
        val tempLinearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
            background = getDrawable(R.drawable.rounder_backround)
        }

        val imageView = ImageView(this).apply {
            val parms = LinearLayout.LayoutParams(-1, 350)
            setImageResource(laptop.image)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
        }


        val titleTextView = TextView(this).apply {
            text = laptop.title
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
            textSize = 20f


        }

        val descTextView = TextView(this).apply {
            text = laptop.description
            val parms = LinearLayout.LayoutParams(-1, -2)
            parms.setMargins(50, 50, 50, 50)
            layoutParams = parms
        }

        tempLinearLayout.addView(imageView)
        tempLinearLayout.addView(titleTextView)
        tempLinearLayout.addView(descTextView)

        return tempLinearLayout
    }
}