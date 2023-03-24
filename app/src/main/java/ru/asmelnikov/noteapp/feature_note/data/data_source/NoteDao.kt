package ru.asmelnikov.noteapp.feature_note.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.asmelnikov.noteapp.feature_note.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}