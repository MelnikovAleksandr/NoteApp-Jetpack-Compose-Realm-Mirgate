package ru.asmelnikov.noteapp.feature_note.domain.use_case

import org.mongodb.kbson.ObjectId
import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm

class DeleteNoteUseCase(
    private val repository: NoteRepositoryRealm
) {

    suspend operator fun invoke(note: NoteRealm) {
        repository.deleteNote(note)
    }
}