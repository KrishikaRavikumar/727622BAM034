package com.analytics.realtime_analytics.model;

public class User {
  private String id;
  private String name;
  private String image;
  private int postCount;

  public User(String id, String name, String image, int postCount) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.postCount = postCount;
  }

  public String getId() { return id; }
  public String getName() { return name; }
  public String getImage() { return image; }
  public int getPostCount() { return postCount; }
}

