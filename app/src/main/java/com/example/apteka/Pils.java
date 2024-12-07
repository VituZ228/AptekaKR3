package com.example.apteka;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Pils extends AppCompatActivity {

    private DatabaseHelper db;
    private LinearLayout pilsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pils);

        db = new DatabaseHelper(this);
        pilsLayout = findViewById(R.id.moviesLayout);

        List<String> movieTitles = db.gettitle();
        List<String> movieDescriptions = db.getdescription();
        List<String> pilsCoast = db.getcoast();

        // Получение списка изображений в формате PNG из базы данных
        List<byte[]> imageBytesList = db.getImagesAsBytes();

        for (int i = 0; i < movieTitles.size(); i++) {
            String title = movieTitles.get(i);
            String description = movieDescriptions.get(i);
            String coast = pilsCoast.get(i);


            TextView movieTextView = new TextView(this);
            movieTextView.setText("Название: " + title + "\nОписание: " + description + "\nЦена: " + coast);
            pilsLayout.addView(movieTextView);

            byte[] imageBytes = imageBytesList.get(i);

            if (imageBytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                ImageView movieImageView = new ImageView(this);
                movieImageView.setImageBitmap(bitmap);
                pilsLayout.addView(movieImageView);
            }


        }
    }
}
