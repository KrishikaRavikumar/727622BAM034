package com.analytics.realtime_analytics.model;

public class Post {
  private String id;
  private String content;
  private String image;
  private int commentCount;
  private User user;

  public Post(String id, String content, String image, int commentCount, User user) {
    this.id = id;
    this.content = content;
    this.image = image;
    this.commentCount = commentCount;
    this.user = user;
  }

  public String getId() { return id; }
  public String getContent() { return content; }
  public String getImage() { return image; }
  public int getCommentCount() { return commentCount; }
  public User getUser() { return user; }
}