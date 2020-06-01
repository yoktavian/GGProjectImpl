package com.vintech.summerclub.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vintech.summerclub.R
import com.vintech.summerclub.adapter.CustomAdapter
import com.vintech.summerclub.data.DataSources
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
        val adapter =
            CustomAdapter(DataSources.dataSources)
        listView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

