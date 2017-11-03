package com.storyteller.storyteller.models;

/**
 * Created by the-invader on 3/26/2017.
 */

public class Post {
    String tag;
    String author_image_url, publish_date, content_image_url, title, content, author_name;
    String link_url;

    public Post(String author_image_url, String publish_date, String content_image_url, String title, String content, String link_url, String author_name) {
        this.author_image_url = author_image_url;
        this.publish_date = publish_date;
        this.content_image_url = content_image_url;
        this.title = title;
        this.content = content;
        this.link_url = link_url;
        this.author_name = author_name;
    }

    public String getAuthor_image_url() {
        return author_image_url;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getContent_image_url() {
        return content_image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink_url() {
        return link_url;
    }

    public String getAuthor_name() {
        return author_name;
    }
}
