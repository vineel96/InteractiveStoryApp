package com.example.vineelabhinav.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vineelabhinav.interactivestory.R;
import com.example.vineelabhinav.interactivestory.model.Page;
import com.example.vineelabhinav.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    private static final String TAG=AppCompatActivity.class.getSimpleName();
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private  String name;
    private Stack<Integer> pageStack= new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        storyImageView=(ImageView)findViewById(R.id.StoryImageView);
        storyTextView=(TextView)findViewById(R.id.StoryTextView);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        choice2Button = (Button)findViewById(R.id.choice2Button);

        Intent i=getIntent();
        name=i.getStringExtra(getString(R.string.key_name));
        Log.d(TAG,name);

        story=new Story();
        loadPage(0);
    }
    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);
        final Page page=story.getPage(pageNumber);

        Drawable image= ContextCompat.getDrawable(this,page.getImageId());
        storyImageView.setImageDrawable(image);
        String pageText=getString(page.getTextId());
        pageText=String.format(pageText,name);
        storyTextView.setText(pageText);
        if(page.isFinalPage())
        {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText("TRY AGAIN");
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice2Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextpage = page.getChoice1().getNextPage();
                loadPage(nextpage);
            }
        });
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextpage = page.getChoice2().getNextPage();
                loadPage(nextpage);
            }
        });
    }
    public void onBackPressed()
    {
        pageStack.pop();
        if(pageStack.isEmpty())
        super.onBackPressed();
        else
        {
            loadPage(pageStack.pop());
        }
    }
}
