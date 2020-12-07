package com.example.feelingsdiary;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class CalendarActivity : AppCompatActivity() {

    var calender: CalendarView? = null
    var date_view: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calender = findViewById<View>(R.id.calender) as CalendarView
        date_view = findViewById<View>(R.id.date_view) as TextView

        // Source: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
        calender!!.setOnDateChangeListener(
            OnDateChangeListener { view, year, month, dayOfMonth ->
                val m = month + 1;
                val date = ( dayOfMonth.toString() + "-" + m + "-" + year)
                date_view!!.text = date
            })
    }

    // source: https://www.tutorialspoint.com/how-to-add-calendar-events-in-android-app
    fun AddCalendarEvent(view: View?) {
        val calendarEvent = Calendar.getInstance()
        val i = Intent(Intent.ACTION_EDIT)
        i.type = "vnd.android.cursor.item/event"
        i.putExtra("startTime", calendarEvent.timeInMillis)
        i.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
        i.putExtra("eventName", "Calendar Event")
        startActivity(i)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            loadFragment(item.itemId)
            true
        }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        var fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.home -> {
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            }
            R.id.profile -> {
                val intent = Intent(this, ProfilePageActivity::class.java)
                startActivity(intent)
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
            }
            R.id.calendar -> {
                Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
            }
            R.id.diary -> {
                val intent = Intent(this,DashboardActivity::class.java )
                startActivity(intent)
                Toast.makeText(getApplicationContext(), "Diary", Toast.LENGTH_SHORT).show();
            }
            else -> {
                null
            }
        }

    }

}
