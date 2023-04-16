package ru.asmelnikov.noteapp.feature_note.presentation.note

import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {

    data class Order(val noteOrder: NoteOrder) : NotesEvent()

    data class DeleteNote(val note: NoteRealm) : NotesEvent()

    object RestoreNote : NotesEvent()

    object ToggleOrderSection : NotesEvent()

}
