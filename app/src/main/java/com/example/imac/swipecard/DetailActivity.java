package com.example.imac.swipecard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String TITLE="title";
    public static final String IMGURL="url";

    private ImageView img;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle=(TextView)findViewById(R.id.tv_title);
        img=(ImageView)findViewById(R.id.img_avatar);

        Intent i=getIntent();

        if(i != null)
        {
            String title=i.getStringExtra(TITLE);
            String url=i.getStringExtra(IMGURL);

            Picasso.with(this).load(url).fit().centerCrop().into(img);
            tvTitle.setText(title);

            Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
            toolbar.setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
