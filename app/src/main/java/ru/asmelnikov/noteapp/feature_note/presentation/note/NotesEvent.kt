package ru.asmelnikov.noteapp.feature_note.presentation.note

import ru.asmelnikov.noteapp.feature_note.domain.model.Note
import ru.asmelnikov.noteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {

    data class Order(val noteOrder: NoteOrder) : NotesEvent()

    data class DeleteNote(val note: Note) : NotesEvent()

    object RestoreNote : NotesEvent()

    object ToggleOrderSection : NotesEvent()

}
