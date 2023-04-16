package ru.asmelnikov.noteapp.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm

interface NoteRepositoryRealm {

    fun getNotes(): Flow<List<NoteRealm>>

    suspend fun getNoteById(id: String): NoteRealm?

    suspend fun insertNote(note: NoteRealm)

    suspend fun deleteNote(note: NoteRealm)
}