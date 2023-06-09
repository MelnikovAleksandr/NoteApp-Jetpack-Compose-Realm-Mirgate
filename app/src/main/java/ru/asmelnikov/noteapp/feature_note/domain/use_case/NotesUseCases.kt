package ru.asmelnikov.noteapp.feature_note.domain.use_case

data class NotesUseCases(

    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase

)
