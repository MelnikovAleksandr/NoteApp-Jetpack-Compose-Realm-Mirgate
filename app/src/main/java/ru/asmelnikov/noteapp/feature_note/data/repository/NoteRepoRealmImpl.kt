package ru.asmelnikov.noteapp.feature_note.data.repository

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm
import javax.inject.Inject

class NoteRepoRealmImpl @Inject constructor(private val realm: Realm) :
    NoteRepositoryRealm {
    override fun getNotes(): Flow<List<NoteRealm>> {
        return realm.query<NoteRealm>().asFlow().map { it.list }
    }

    override suspend fun getNoteById(id: String): NoteRealm {
        var note: NoteRealm? = null
        val objectId = ObjectId(id)
        realm.write {
            note = query<NoteRealm>(query = "id == $0", objectId).first().find()
        }
        return note ?: throw IllegalArgumentException("No note found with id $id")
    }

    override suspend fun insertNote(note: NoteRealm) {
        realm.write { copyToRealm(note) }
    }

    override suspend fun deleteNote(note: NoteRealm) {
        realm.write {
            val deleteNote = query<NoteRealm>(query = "id == $0", note.id).first().find()
            try {
                deleteNote?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("RepositoryImpl", "${e.message}")
            }
        }
    }
}