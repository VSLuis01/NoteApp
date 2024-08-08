package br.com.luise.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.luise.noteapp.model.Note
import br.com.luise.noteapp.util.DateConverter
import br.com.luise.noteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDataBaseDao
}