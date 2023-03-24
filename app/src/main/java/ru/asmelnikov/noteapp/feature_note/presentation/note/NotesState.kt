package ru.asmelnikov.noteapp.feature_note.presentation.note

import ru.asmelnikov.noteapp.feature_note.domain.model.Note
import ru.asmelnikov.noteapp.feature_note.domain.util.NoteOrder
import ru.asmelnikov.noteapp.feature_note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
