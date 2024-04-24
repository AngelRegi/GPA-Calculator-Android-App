/*
 * Assignment : InClass12
 * FileName : AddCourseFragment.java
 * Student(s) Name : Angel Regi Chellathurai Vijayakumari
 * */

package edu.uncc.inclass12;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import edu.uncc.inclass12.databinding.FragmentAddCourseBinding;

public class AddCourseFragment extends Fragment {

    public static String TAG = "grade";
    public AddCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddCourseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Course");
        MainActivity.currentMenuState = MainActivity.menuStateHide;
        getActivity().invalidateOptionsMenu();
        binding.buttonCancel.setOnClickListener(v -> {

            mListener.goToGradesFragment();
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseNumber = binding.editTextCourseNumber.getText().toString();
                String courseName = binding.editTextCourseName.getText().toString();
                //double courseHours = Double.parseDouble(binding.editTextCourseHours.getText().toString());
                String courseHours = binding.editTextCourseHours.getText().toString();

                int selectedId = binding.radioGroupGrades.getCheckedRadioButtonId();

                if(courseName.isEmpty() || courseNumber.isEmpty() || binding.editTextCourseHours.getText().toString().isEmpty()) {
                   Toast.makeText(getContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                   return;
                }
                if(selectedId == -1){
                    Toast.makeText(getContext(), "Please select a letter grade !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!binding.editTextCourseHours.getText().toString().isEmpty()) {
                    Integer creditHours = Integer.parseInt(courseHours);
                    if(creditHours!=1 && creditHours != 2 && creditHours!=3 ) {
                        Toast.makeText(getContext(), "Please select course hour in the range 1 to 3 !!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                    String courseLetterGrade;
                    if(selectedId == R.id.radioButtonA) {
                        courseLetterGrade = "A";
                    } else if(selectedId == R.id.radioButtonB) {
                        courseLetterGrade = "B";
                    } else if(selectedId == R.id.radioButtonC) {
                        courseLetterGrade = "C";
                    } else if(selectedId == R.id.radioButtonD) {
                        courseLetterGrade = "D";
                    } else {
                        courseLetterGrade = "F";
                    }

                    Grade grade = new Grade();
                    String uniqueID = UUID.randomUUID().toString();
                    grade.courseId = uniqueID;
                    grade.courseNumber = courseNumber;
                    grade.courseName = courseName;
                    grade.courseGrade = courseLetterGrade;
                    grade.creditHours = courseHours;
                    grade.createdBy =  uniqueID;
                    Date postDate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                    String strDate = formatter.format(postDate);
                    AppDatabase db = Room.databaseBuilder(getActivity(), AppDatabase.class, "grade.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
                    db.gradeDao().insertAll(grade);
                Log.d(TAG, "onCreate: " + db.gradeDao().getAll());
                mListener.goToGradesFragment();

            }
        });

    }

    AddCourseListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddCourseListener) context;
    }

    interface AddCourseListener {
        void goToGradesFragment();
    }
}