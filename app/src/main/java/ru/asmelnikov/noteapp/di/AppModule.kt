package ru.asmelnikov.noteapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.asmelnikov.noteapp.feature_note.data.data_source.NoteDatabase
import ru.asmelnikov.noteapp.feature_note.data.repository.NoteRepositoryImpl
import ru.asmelnikov.noteapp.feature_note.domain.repository.NoteRepository
import ru.asmelnikov.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import ru.asmelnikov.noteapp.feature_note.domain.use_case.GetNotesUseCase
import ru.asmelnikov.noteapp.feature_note.domain.use_case.NotesUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }

}