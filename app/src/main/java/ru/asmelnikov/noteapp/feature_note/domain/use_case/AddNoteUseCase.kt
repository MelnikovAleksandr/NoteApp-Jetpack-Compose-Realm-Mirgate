package ru.asmelnikov.noteapp.feature_note.domain.use_case

import ru.asmelnikov.noteapp.feature_note.domain.model.NoteRealm
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepositoryRealm

class AddNoteUseCase(
    private val repository: NoteRepositoryRealm
) {
    @Throws(NoteRealm.InvalidNoteException::class)
    suspend operator fun invoke(note: NoteRealm) {
        if (note.title.isBlank()) {
            throw NoteRealm.InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw NoteRealm.InvalidNoteException("The content of the note can't be empty")
        }
        repository.insertNote(note)

    }
}
