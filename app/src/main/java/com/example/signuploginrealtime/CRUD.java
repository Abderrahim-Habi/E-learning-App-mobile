package com.example.signuploginrealtime;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CRUD extends AppCompatActivity {
    private DataBaseHelper dbHelper;
    private EditText editTextTitle, editTextCategory, editTextImage, editTextDescription, editTextVideo;
    private Button buttonAdd, buttonView;
    private ListView listViewCourses;

    private ArrayList<Course_Crud> courseList;
    private CourseAdapter_Crud adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        // Initialize database helper
        dbHelper = new DataBaseHelper(this);

        // Initialize UI elements
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextImage = findViewById(R.id.editTextImage);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextVideo = findViewById(R.id.editTextVideo);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);
        listViewCourses = findViewById(R.id.listViewCourses);

        // Initialize list and adapter
        courseList = new ArrayList<>();
        adapter = new CourseAdapter_Crud(this, courseList);
        listViewCourses.setAdapter(adapter);

        // Add course
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString().trim();
                String category = editTextCategory.getText().toString().trim();
                String image = editTextImage.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String video = editTextVideo.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(CRUD.this, "Title and Description are required!", Toast.LENGTH_SHORT).show();
                } else {
                    long result = dbHelper.insertCourse(title, category, image, description, video);
                    if (result != -1) {
                        Toast.makeText(CRUD.this, "Course added successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    } else {
                        Toast.makeText(CRUD.this, "Error adding course!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // View courses
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCourses();
            }
        });
    }

    private void clearFields() {
        editTextTitle.setText("");
        editTextCategory.setText("");
        editTextImage.setText("");
        editTextDescription.setText("");
        editTextVideo.setText("");
    }

    private void loadCourses() {
        Cursor cursor = dbHelper.getAllCourses(); // Ensure this returns a Cursor object.
        if (cursor != null && cursor.getCount() > 0) {
            courseList.clear();
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_TITLE));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CATEGORY));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_IMAGE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_VIDEO_YOUTUBE));

                courseList.add(new Course_Crud(id, title, category, image, description, video));
            }
            cursor.close(); // Close the cursor to avoid memory leaks
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No courses found!", Toast.LENGTH_SHORT).show();
        }
    }
}
