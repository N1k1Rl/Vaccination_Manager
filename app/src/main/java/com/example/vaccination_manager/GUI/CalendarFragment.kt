package com.example.vaccination_manager.GUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.vaccination_manager.R
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : DialogFragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var setDateButton: Button
    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarView = view.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            selectedDate = dateFormat.format(calendar.time)
        }

        calendarView.setOnClickListener {
            val addVacc = activity as? AddVacc
            addVacc?.setDate(selectedDate ?: "")

            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        return view
    }
}
