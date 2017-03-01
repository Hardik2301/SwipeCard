package com.example.imac.swipecard;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imac.swipecard.viewUtil.SwipeDeck;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private Context context = this;
    private SwipeDeckAdapter adapter;
    private List<CardItem> testData;

    private FloatingActionButton btn_left,btn_right,btn_undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        testData = new ArrayList<CardItem>();
        AddDummyData();

        adapter = new SwipeDeckAdapter(testData, this);
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + stableId);
            }

            @Override
            public void cardSwipedRight(long stableId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + stableId);
            }

            @Override
            public void LeftToRightSwipe() { // swipe running
                btn_right.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_green_dark)));
                btn_left.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            }

            @Override
            public void RightToLeftSwipe() { // swipe running
                btn_left.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_red_dark)));
                btn_right.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            }

            @Override
            public void SwipeFinished() {
                btn_left.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                btn_right.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            }

            @Override
            public void OnCardClick(int position,View card) {
                ImageView imageView =(ImageView) card.findViewById(R.id.offer_image);
                TextView tvview =(TextView) card.findViewById(R.id.sample_text);

                Intent i=new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra(DetailActivity.TITLE,testData.get(position).getName());
                i.putExtra(DetailActivity.IMGURL,testData.get(position).getAvatarUrl());

                Pair<View, String> p1 = Pair.create((View) imageView, "profile");
                Pair<View, String> p2 = Pair.create((View)tvview, "title");

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this,imageView, "profile");
                startActivity(i,options.toBundle());
            }

            @Override
            public boolean isDragEnabled(long itemId) {
                return true;
            }
        });

        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);

        btn_left = (FloatingActionButton) findViewById(R.id.button_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(1000);

            }
        });
        btn_right = (FloatingActionButton) findViewById(R.id.button_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(1000);
            }
        });

        btn_undo = (FloatingActionButton) findViewById(R.id.button_center);
        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testData.add("a sample string.");
//                adapter.notifyDataSetChanged();
                cardStack.unSwipeCard();
            }
        });
    }

    private void AddDummyData() {
        testData.add(new CardItem("1","Sample 1","http://acrossandabroad.com/wp-content/uploads/2014/01/silverton-webb.jpg"));
        testData.add(new CardItem("2","Sample 2","http://howng.com/wp-content/uploads/2016/04/Girl-Rome-selfie-shutterstock_152914343.jpg"));
        testData.add(new CardItem("3","Sample 3","http://static1.squarespace.com/static/52f292abe4b006f4c05205c5/5437f30ce4b02d632f5bac69/5437f30de4b0bc578243b111/1412952846312/02n20selfie-479661.jpg"));
        testData.add(new CardItem("4","Sample 4","https://entwickler.de/wp-content/uploads/2015/01/selfie.jpg"));
        testData.add(new CardItem("5","Sample 5","http://www.viaggiaregratis.eu/wp-content/uploads/2014/03/fotografi-russi-grattacieli-5.jpg"));
        testData.add(new CardItem("6","Sample 6","http://sparklin.com/pixel/wp-content/uploads/2014/05/chacon-selfie-video.jpg"));
    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<CardItem> data;
        private Context context;

        public SwipeDeckAdapter(List<CardItem> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.listitem, parent, false);
            }
            //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
            CardItem item=data.get(position);
            ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
            Picasso.with(context).load(item.getAvatarUrl()).fit().centerCrop().into(imageView);
            TextView textView = (TextView) v.findViewById(R.id.sample_text);
            textView.setText(item.getName());

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                    Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
                    /*Intent i = new Intent(v.getContext(), BlankActivity.class);
                    v.getContext().startActivity(i);*/
                }
            });
            return v;
        }
    }
}
