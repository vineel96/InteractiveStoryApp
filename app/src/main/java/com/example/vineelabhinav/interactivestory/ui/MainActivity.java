package com.example.vineelabhinav.interactivestory.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vineelabhinav.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.nameEditText);
        button=(Button)findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();
                startStory(name);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        editText.setText("");
    }

    private void startStory(String name) {
    Intent i=new Intent(this,StoryActivity.class);
        Resources r=getResources();
        String key=r.getString(R.string.key_name);
    i.putExtra(key,name);
    startActivity(i);
    }
}
