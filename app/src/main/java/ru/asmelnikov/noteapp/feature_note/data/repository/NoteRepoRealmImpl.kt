package ru.asmelnikov.noteapp.feature_note.data.repository

import android.util.Log
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.platform.runBlocking
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
        val objectId = ObjectId(id)
        return runBlocking {
            realm.write {
                val findNote = query<NoteRealm>(query = "id == $0", objectId).first().find()
                findNote ?: throw IllegalArgumentException("No note found with id $id")
            }
        }
    }

    override suspend fun insertNote(note: NoteRealm) {
        realm.write {
            copyToRealm(note, updatePolicy = UpdatePolicy.ALL) }
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