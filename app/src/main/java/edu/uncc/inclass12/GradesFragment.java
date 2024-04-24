/*
 * Assignment : InClass12
 * FileName : GradesFragment.java
 * Student(s) Name : Angel Regi Chellathurai Vijayakumari
 * */

package edu.uncc.inclass12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uncc.inclass12.databinding.FragmentGradesBinding;
import edu.uncc.inclass12.databinding.GradeRowItemBinding;

public class GradesFragment extends Fragment {

    AppDatabase db;
    CourseAdapter courseAdapter;
    ArrayList<Grade> mGrades = new ArrayList<>();

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    FragmentGradesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGradesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.currentMenuState = MainActivity.menuStateShow;
        getActivity().invalidateOptionsMenu();
        getActivity().setTitle("Grades");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        courseAdapter = new CourseAdapter();
        binding.recyclerView.setAdapter(courseAdapter);
        getGrades();

    }

    class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
        @NonNull
        @Override
        public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            GradeRowItemBinding binding = GradeRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CourseViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
            Grade grade = mGrades.get(position);
            holder.setupUI(grade);
        }

        @Override
        public int getItemCount() {
            return mGrades.size();
        }

        class CourseViewHolder extends RecyclerView.ViewHolder {
            GradeRowItemBinding mBinding;
            Grade mGrade;
            public CourseViewHolder(GradeRowItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            public void setupUI(Grade grade){
                mGrade = grade;
                mBinding.textViewCourseNumber.setText(mGrade.getCourseNumber());
                mBinding.textViewCourseName.setText(mGrade.getCourseName());
                mBinding.textViewCourseHours.setText(mGrade.getCreditHours() + " Credit Hours");
                mBinding.textViewCourseLetterGrade.setText(mGrade.getCourseGrade());
                mBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Grade grade = db.gradeDao().findById(mGrade.getId());
                        if(grade != null) {
                            db.gradeDao().delete(grade);
                            getGrades();
                        } else {
                            Toast.makeText(getActivity(), "Error deleting the record", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }

    }
    private void  getGrades() {
        db = Room.databaseBuilder(getActivity(), AppDatabase.class, "grade.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        mGrades = (ArrayList<Grade>) db.gradeDao().getAll();
        courseAdapter.notifyDataSetChanged();
        calculateGPA();
       // mGrades.add(new Grade(document.getString("courseName"), document.getString("courseGrade"),document.getString("courseNumber"), document.getString("creditHours"), document.getId(), document.getString("createdBy") ));
    }

    private void calculateGPA() {
        double totalGradePoints = 0.0;
        double totalCreditHours = 0.0;
        HashMap<String, Double> gpaMap = new HashMap<String, Double>();
        double GPA = 0.0;
        gpaMap.put("A", 4.0); gpaMap.put("B", 3.0); gpaMap.put("C", 2.0); gpaMap.put("D", 1.0);
        gpaMap.put("F", 0.0);
        for (Grade grade: mGrades) {
            totalGradePoints += (gpaMap.get(grade.getCourseGrade()) * Double.parseDouble(grade.creditHours));
            totalCreditHours += Double.parseDouble(grade.creditHours);
        }
        GPA = totalGradePoints / totalCreditHours;
        DecimalFormat df = new DecimalFormat("0.00");

        if(totalCreditHours == 0.0) {
            binding.textViewGPA.setText("GPA: 4.00");
        } else {
            binding.textViewGPA.setText("GPA: " + df.format(GPA) + "");
        }
        binding.textViewHours.setText("Hours: " + df.format(totalCreditHours) + "");
        // return GPA;
    }
}