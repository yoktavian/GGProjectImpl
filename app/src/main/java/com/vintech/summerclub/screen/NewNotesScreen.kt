package com.vintech.summerclub.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vintech.summerclub.data.DataSources
import com.vintech.summerclub.data.Note
import com.vintech.summerclub.R
import kotlinx.android.synthetic.main.create_new_note_screen.*

class NewNotesScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_note_screen)
        title = "Note"
        initializeOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        clearDataOnTextField()
    }

    private fun clearDataOnTextField() {
        noteTitle.text.clear()
        noteContent.text.clear()
    }

    private fun initializeOnClickListener() {
        submit.setOnClickListener {
            onSubmitButtonClicked()
        }
    }

    private fun onSubmitButtonClicked() {
        when {
            !isTitleAndContentNotEmpty() -> {
                showErrorMessage("Title dan note tidak boleh kosong")
            }
            !isTncChecked() -> {
                showErrorMessage("Checklist tnc untuk menyimpan catatan")
            }
            else -> {
                saveNewNote(
                    title = noteTitle.text.toString(),
                    content = noteContent.text.toString()
                )
                // go to my notes screen after success save the note.
                goToMyNotesScreen()
            }
        }
    }

    private fun isTncChecked() = tncCheckBox.isChecked

    private fun isTitleAndContentNotEmpty() : Boolean {
        val isTitleNotEmpty = noteTitle.text.isNotEmpty()
        val isContentNotEmpty = noteContent.text.isNotEmpty()
        return isTitleNotEmpty && isContentNotEmpty
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun saveNewNote(title: String, content: String) {
        DataSources.dataSources.add(Note(title, content))
    }

    private fun goToMyNotesScreen() {
        val intent = Intent(this, MyNotesScreen::class.java)
        startActivity(intent)
    }
}

