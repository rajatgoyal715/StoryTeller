package com.storyteller.storyteller;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedPostActivity extends AppCompatActivity {

    String author_image_url, content, title, publish_date, content_image_url, link_url, author_name;

    CircleImageView authorImage;
    TextView authorName, publishDate;
    ImageView contentImage, download;
    TextView postTitle, postContent;

    FloatingActionButton fab;

    MediaPlayer player;

    String audioUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        Intent i = getIntent();
        author_image_url = i.getStringExtra("author_image_url");
        title = i.getStringExtra("title");
        content = i.getStringExtra("content");
        content_image_url = i.getStringExtra("content_image_url");
        publish_date = i.getStringExtra("publish_date");
        link_url = i.getStringExtra("link_url");
        author_name = i.getStringExtra("author_name");

        authorImage = (CircleImageView) findViewById(R.id.author_image);
        authorName = (TextView) findViewById(R.id.author_name);
        publishDate = (TextView) findViewById(R.id.publish_date);
        contentImage = (ImageView) findViewById(R.id.contentImage);
        postTitle = (TextView) findViewById(R.id.postTitle);
        postContent = (TextView) findViewById(R.id.postContent);
        download = (ImageView) findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //download the mp3 file
                Toast.makeText(DetailedPostActivity.this, "Download Button implementation remaining.", Toast.LENGTH_SHORT).show();
            }
        });

        player = MediaPlayer.create(DetailedPostActivity.this, R.raw.music);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // play the media
                playAndPause();
            }
        });

        setup();

        // fetchAudio();

        // setupMediaPlayer();


    }

    public void playAndPause() {
        if (player.isPlaying()) {
            player.stop();
//            fab.setBackground(getResources().getDrawable(R.drawable.play));
            fab.setImageResource(R.drawable.play);
        } else {
            player.start();
            fab.setImageResource(R.drawable.pause);
        }
    }

    public void setup() {
        Picasso.with(getApplicationContext()).load(author_image_url).into(authorImage);
        if (author_name.isEmpty())
            author_name = "Scott Santens";
        authorName.setText(author_name);
        publishDate.setText(publish_date);
        Picasso.with(getApplicationContext()).load(content_image_url).into(contentImage);
        postTitle.setText(title);
        postContent.setText(content);
    }

//    public void setupMediaPlayer() {
//        try {
//            player = MediaPlayer.create(this, R.raw.music);
//            player.start();
////            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
////            player.setDataSource("");
////            player.set
////            player.prepare();
//        } catch (Exception e) {
//            // Handle Exception
//            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }

    public void fetchAudio() {
        audioUrl = getString(R.string.music_url);
    }

}
