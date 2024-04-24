/*
 * Assignment : InClass12
 * FileName : MainActivity.java
 * Student(s) Name : Angel Regi Chellathurai Vijayakumari
 * */

package edu.uncc.inclass12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements AddCourseFragment.AddCourseListener{

    public static Menu MENU = null;
    public static String currentMenuState = "SHOW";
    public static String menuStateHide = "HIDE";
    public static String menuStateShow = "SHOW";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MENU = menu;
        if (currentMenuState == menuStateHide)
        {
            for (int i = 0; i < menu.size(); i++)
                menu.getItem(i).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.rootView, new AddCourseFragment())
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new GradesFragment())
                .commit();
    }



    @Override
    public void goToGradesFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new GradesFragment())
                .commit();
    }
}