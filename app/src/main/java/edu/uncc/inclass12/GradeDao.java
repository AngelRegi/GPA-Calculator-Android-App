/*
 * Assignment : InClass12
 * FileName : GradeDao.java
 * Student(s) Name : Angel Regi Chellathurai Vijayakumari
 * */

package edu.uncc.inclass12;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradeDao {

    @Query("SELECT * FROM grades")
    List<Grade> getAll();

    @Query("SELECT * FROM grades WHERE id = :id limit 1")
    Grade findById(long id);

    @Update
    void update(Grade note);

    @Insert
    void insertAll(Grade ...grades);

    @Delete
    void delete(Grade note);
}
