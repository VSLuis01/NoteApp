package br.com.luise.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.luise.noteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDataBaseDao
}