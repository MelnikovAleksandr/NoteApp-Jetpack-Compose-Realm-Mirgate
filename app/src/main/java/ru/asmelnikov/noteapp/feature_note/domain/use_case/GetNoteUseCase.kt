package ru.asmelnikov.noteapp.feature_note.domain.use_case

import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm

class GetNoteUseCase(
    private val repository: NoteRepositoryRealm
) {

    suspend operator fun invoke(id: String): NoteRealm? {
        return repository.getNoteById(id)
    }
}