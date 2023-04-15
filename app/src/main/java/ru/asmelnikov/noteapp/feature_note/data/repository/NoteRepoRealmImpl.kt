package ru.asmelnikov.noteapp.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.noteapp.feature_note.data.data_source.NoteDaoRealm
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm

class NoteRepoRealmImpl(private val dao: NoteDaoRealm) : NoteRepositoryRealm {
    override fun getNotes(): Flow<List<NoteRealm>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): NoteRealm? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: NoteRealm) {
        dao.insert(note)
    }

    override suspend fun deleteNote(note: NoteRealm) {
        dao.delete(note)
    }
}