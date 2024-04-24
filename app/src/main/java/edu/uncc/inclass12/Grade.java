/*
 * Assignment : InClass12
 * FileName : Grade.java
 * Student(s) Name : Angel Regi Chellathurai Vijayakumari
 * */

package edu.uncc.inclass12;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grades")
public class Grade {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String courseName;

    @ColumnInfo
    public String courseGrade;

    @ColumnInfo
    public String courseNumber;

    @ColumnInfo
    public String creditHours;

    @ColumnInfo
    public String courseId;

    @ColumnInfo
    public String createdBy;

    public Grade() {
    }

    public Grade(String courseName, String courseGrade, String courseNumber, String creditHours, String courseId, String createdBy) {
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.courseId = courseId;
        this.createdBy = createdBy;
    }

    public Grade(long id, String courseName, String courseGrade, String courseNumber, String creditHours, String courseId, String createdBy) {
        this.id = id;
        this.courseName = courseName;
        this.courseGrade = courseGrade;
        this.courseNumber = courseNumber;
        this.creditHours = creditHours;
        this.courseId = courseId;
        this.createdBy = createdBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseGrade='" + courseGrade + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", creditHours='" + creditHours + '\'' +
                ", courseId='" + courseId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
