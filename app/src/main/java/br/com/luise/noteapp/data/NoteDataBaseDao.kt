package br.com.luise.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.luise.noteapp.model.Note

@Dao
interface NoteDataBaseDao {

    @Query("SELECT * FROM notes_tbl")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes_tbl WHERE id = :id")
    fun getNote(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)

}
