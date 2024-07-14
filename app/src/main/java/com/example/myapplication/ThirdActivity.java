package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {
    ListView listView;
    DB mydb1 = new DB(ThirdActivity.this);
    ArrayList<Object> info = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        listView = findViewById(R.id.gg3);



        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("contents");
        //myRef.addValueEventListener(new ValueEventListener() {
        //@Override
        //public void onDataChange(@NonNull DataSnapshot snapshot) {
        //Map<Integer, String> map = (Map<Integer, String>) snapshot.getValue();
        //List<String> list = new ArrayList<String>(map.values());

        funkcia();
        ArrayAdapter adapter = new ArrayAdapter<>(ThirdActivity.this, android.R.layout.simple_list_item_1, info);
        listView.setAdapter(adapter);
        ;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem= (String) adapter.getItem(position);
                long primaryKey = mydb1.getPrimaryKeyFromSQLite(selectedItem);

                // Display primary key in a toast message or use it as needed
                // ();
                //System.out.println(primaryKey);
                mydb1.deleteData(primaryKey);

                Toast.makeText(ThirdActivity.this, "ამოიშალა ფავორიტებიდან " , Toast.LENGTH_LONG).show();

            }
        });





    }


    //@Override
    //public void onCancelled(@NonNull DatabaseError error) {

    //}

    //});
    void funkcia() {
        Cursor cursor = mydb1.readAllData();
        while (cursor.moveToNext()) {
            info.add(cursor.getString(1));


        }


    }


}
















