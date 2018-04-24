package com.example.regis.medsystem.research;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.regis.medsystem.R;

public class ArticleContent extends AppCompatActivity {
    TextView title, author, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_content);
        Bundle bundle = getIntent().getExtras();
        title = (TextView) findViewById(R.id.titleTextContent);
        author = (TextView) findViewById(R.id.authorTextContent);
        content = (TextView) findViewById(R.id.contentTextContent);
        title.setText(bundle.getString("titleText"));
        author.setText(bundle.getString("authorName"));
        content.setText(bundle.getString("content"));


    }
}
