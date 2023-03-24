package ru.asmelnikov.noteapp.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.noteapp.feature_note.domain.model.Note


interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}