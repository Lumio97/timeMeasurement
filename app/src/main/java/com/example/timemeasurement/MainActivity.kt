package com.example.timemeasurement

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var startTime: Long= 0
        var endTime: Long = 0

        val entries = mutableListOf<Entry>()


        pressButton.setOnClickListener {
            endTime = System.currentTimeMillis()
            rectangle.visibility= View.INVISIBLE
            val time = (endTime - startTime)

            if(startTime != 0L) {
                Log.i("Time", time.toString())
                entries.add(Entry((entries.size + 1).toFloat(), time.toFloat()))
                val lineDataSet = LineDataSet(entries, "Time").also {
                    it.color = Color.BLUE
                    it.valueTextColor = Color.MAGENTA
                }
                chart.data = LineData(lineDataSet)
                chart.invalidate()
            }
                pressButton.isEnabled = false
                Handler().postDelayed({
                    rectangle.visibility = View.VISIBLE
                    pressButton.isEnabled = true
                    startTime = System.currentTimeMillis()
                }, 3000L + (0..1000L).random())
            }
        }
    }

