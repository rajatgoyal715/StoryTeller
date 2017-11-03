package com.storyteller.storyteller;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.storyteller.storyteller.models.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {

    private ArrayList<String> tags;
    private ArrayList<Post> postsList;

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        tags = getIntent().getStringArrayListExtra("Tags");

        recyclerView = (RecyclerView) findViewById(R.id.postsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postsList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        updateList();

        // fetchPosts();
        readJson();
    }

    public void updateList() {
        postAdapter = new PostAdapter(postsList, getApplicationContext());
        recyclerView.setAdapter(postAdapter);
    }

    public void readJson() {
        for (int i = 0; i < tags.size(); i++) {
            try {
                JSONArray array = new JSONArray(loadJSONFromAsset(tags.get(i)));
                int index = 0;
                while (index < 5) {
                    JSONObject object = array.getJSONObject(index);
                    String author_image_url = object.getString("pic");
                    String publish_date = object.getString("date");
                    String content_image_url = object.getString("photo");
                    String title = object.getString("head");
                    String content = object.getString("contents");
                    String link_url = object.getString("link");
                    String author_name = object.getString("author");

                    postsList.add(((i + 1) * index), new Post(author_image_url, publish_date,
                            content_image_url, title, content, link_url, author_name));
                    index++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadJSONFromAsset(String tag) {
        String json = null;
        try {
            String fileName = tag + ".json";
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /*public void fetchPosts() {
        if(!checkInternet()) {
            Toast.makeText(this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0 ; i < tags.size(); i++) {
            String tag = tags.get(i);

            final String url = "https://medium.com/tag/" + tag;

//            String BASE_URL = "192.168.2.229/medium/lol.php?tag=" + url;
            String BASE_URL = "192.168.2.229/medium/lol.php";

            final int position = i;
            JsonArrayRequest request = new JsonArrayRequest(
                    BASE_URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                int index = 0;
                                while(index < 5) {
                                    JSONObject object = response.getJSONObject(index);
                                    String author_image_url = object.getString("pic");
                                    String publish_date = object.getString("date");
                                    String content_image_url = object.getString("photo");
                                    String title = object.getString("head");
                                    String content = object.getString("contents");
                                    String link_url = object.getString("link");
                                    String author_name = object.getString("author");

                                    postsList.add(((position+1)*index), new Post(author_image_url, publish_date,
                                            content_image_url, title, content, link_url, author_name));
                                    index++;
                                }
                            } catch (JSONException e) {
                                Toast.makeText(PostsActivity.this, "JSONException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            updateList();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PostsActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("tag", url);
                    return params;
                }
            };

            requestQueue.add(request);

        }
    }*/

    public boolean checkInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
}
