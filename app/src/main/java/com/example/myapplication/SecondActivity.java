package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecondActivity extends AppCompatActivity {
    ListView listview;
    DB mydb = new DB(SecondActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listview = findViewById(R.id.list);
        //SwipeRefreshLayout srl = findViewById(R.id.swipeToRefresh);

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("contents");





        ;
        String[] paragraphArray = new String[50]; // Define paragraphArray outside Runnable if necessary


// Your existing code for initializing the Runnable

        Runnable runnable = new Runnable() {

            final String site = "https://rakhdeba.com";

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(site).get();
                    Elements paragraphs = doc.select("p");

                    // Create an array to store paragraph texts
                    String[] paragraphArray = new String[paragraphs.size()];

                    // Collect the paragraphs
                    for (int i = 0; i < paragraphs.size(); i++) {
                        paragraphArray[i] = paragraphs.get(i).text();

                    }

                    // Optionally print the array (for debugging)
                    for (String paragraph : paragraphArray) {
                        System.out.println(paragraph);
                    }

                    // Update the UI on the main thread with the fetched data
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ArrayAdapter adapter = new ArrayAdapter<>(SecondActivity.this, android.R.layout.simple_list_item_1, paragraphArray);


                            listview.setAdapter(adapter);
                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //myRef.push().setValue(Arrays.asList(paragraphArray[position]));
                                    mydb.addinfo(paragraphArray[position].trim());

                                    Toast.makeText(SecondActivity.this,"დაემატა ფავორიტებში", Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();




    }
}






