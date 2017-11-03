package com.storyteller.storyteller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.storyteller.storyteller.models.Post;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by the-invader on 3/26/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> postsList;
    private Context context;

    public PostAdapter(ArrayList<Post> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item_post, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        final Post post = postsList.get(position);
        Picasso.with(context).load(post.getAuthor_image_url()).into(holder.authorImageView);
        holder.title.setText(post.getTitle());
        holder.publishDate.setText(post.getPublish_date());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailedPost(post);
            }
        });
    }

    public void openDetailedPost(Post post) {
        Intent i = new Intent(context, DetailedPostActivity.class);
        i.putExtra("author_image_url", post.getAuthor_image_url());
        i.putExtra("title", post.getTitle());
        i.putExtra("content_image_url", post.getContent_image_url());
        i.putExtra("content", post.getContent());
        i.putExtra("publish_date", post.getPublish_date());
        i.putExtra("link_url", post.getLink_url());
        i.putExtra("author_name", post.getAuthor_name());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        CircleImageView authorImageView;
        TextView title, publishDate;

        public PostViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            authorImageView = (CircleImageView) itemView.findViewById(R.id.authorImageView);
            title = (TextView) itemView.findViewById(R.id.title);
            publishDate = (TextView) itemView.findViewById(R.id.publishDate);
        }
    }
}
