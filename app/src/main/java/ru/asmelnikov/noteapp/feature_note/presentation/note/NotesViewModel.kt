package ru.asmelnikov.noteapp.feature_note.presentation.note

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.asmelnikov.noteapp.feature_note.domain.use_case.NotesUseCases
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {



}