package com.example.regis.medsystem.research;

/**
 * Created by Regis on 23-04-2018.
 */

public class ArticleClass {
    String title, authorName, content;

    public ArticleClass(String title, String authorName, String content) {
        this.setTitle(title);
        this.setAuthorName(authorName);
        this.setContent(content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
