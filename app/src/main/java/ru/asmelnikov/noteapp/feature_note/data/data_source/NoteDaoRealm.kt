package ru.asmelnikov.noteapp.feature_note.data.data_source

import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import javax.inject.Inject

class NoteDaoRealm @Inject constructor(private val realm: Realm) {

    fun getNotes(): Flow<List<NoteRealm>> {
        return flow<List<NoteRealm>> {
            val realm = Realm.getDefaultInstance()
            val notes = realm.where(NoteRealm::class.java).findAll()
            val notesList = realm.copyFromRealm(notes)
            emit(notesList)
            realm.close()
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNoteById(id: Int): NoteRealm? {
        return realm.where(NoteRealm::class.java).equalTo("id", id).findFirst()
    }

    suspend fun insert(note: NoteRealm) {
        withContext(Dispatchers.IO) {
            val realmConfig = RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build()
            val realm = Realm.getInstance(realmConfig)
            realm.executeTransaction { realm ->
                val currentIdNum = realm.where(NoteRealm::class.java).max("id")
                val nextId = (currentIdNum?.toInt() ?: 0) + 1
                note.id = nextId
                realm.copyToRealmOrUpdate(note)
            }
            realm.close()
        }
    }


    suspend fun delete(note: NoteRealm) {
        realm.executeTransactionAsync { realm ->
            val result = realm.where(NoteRealm::class.java).equalTo("id", note.id).findFirst()
            result?.deleteFromRealm()
        }
    }
}
