package com.vintech.summerclub.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vintech.summerclub.R
import com.vintech.summerclub.data.DataSources
import com.vintech.summerclub.data.Note
import kotlinx.android.synthetic.main.custom_layout_listview.view.*
import kotlinx.android.synthetic.main.notes_screen.*

class MyNotesScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_screen)
        initializeAppBar()
        initializeAdapter()
    }

    private fun initializeAppBar() {
        title = "Notes"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeAdapter() {
        val adapter = CustomAdapter(DataSources.dataSources)
        listView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

class CustomAdapter(private val data: ArrayList<Note>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.custom_layout_listview, parent, false)
        // scooping function.
        view.apply {
            noteTitle.text = data[position].title
            noteContent.text = data[position].content
        }
        view.setOnClickListener {
            if (parent != null) {
                Toast.makeText(parent.context, data[position].title, Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItem(position: Int) = data[position]
    override fun getCount() = data.size
}