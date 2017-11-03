package com.storyteller.storyteller;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MediumActivity extends AppCompatActivity {

    boolean tags[];
    String list[] = {"Python", "CSS", "Design", "UX", "Web-Design", "ML", "PHP", "Cooking", "Javascript", "Painting", "Java", "Gaming"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        String name = getIntent().getStringExtra("Name");
//        Toast.makeText(this, "Name: " + name, Toast.LENGTH_SHORT).show();

        FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
//        flexboxLayout.setFlexDirection(FlexboxLayout.FLEX_DIRECTION_ROW);

        tags = new boolean[list.length];

        for (int i = 0; i < list.length; i++) {
            final CardView cardView = new CardView(this);

            final TextView textView = new TextView(this);
            textView.setText(list[i]);
            textView.setTextSize(dpToPx(8));
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(dpToPx(10), dpToPx(5), dpToPx(10), dpToPx(5));
//            textView.setLayoutParams(lp);
            textView.setPadding(dpToPx(10), dpToPx(5), dpToPx(10), dpToPx(5));

            cardView.setElevation(dpToPx(2));
            cardView.setRadius(dpToPx(5));
            cardView.setContentPadding(dpToPx(2), dpToPx(2), dpToPx(2), dpToPx(2));
//            cardView.setAlpha(0);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            cardView.addView(textView);

            final int index = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!tags[index]) {
                        tags[index] = true;
                        textView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    } else {
                        tags[index] = false;
                        textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
//            params.order = -1;
//            params.flexGrow = 2;

            cardView.setLayoutParams(params);

            flexboxLayout.addView(cardView, i);
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void next(View v) {
        ArrayList<String> tag = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (tags[i])
                tag.add(list[i]);
        }

        Intent i = new Intent(MediumActivity.this, PostsActivity.class);
        i.putStringArrayListExtra("Tags", tag);
        startActivity(i);
    }
}
