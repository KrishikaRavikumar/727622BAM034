package com.analytics.realtime_analytics.service;

import com.analytics.realtime_analytics.model.Post;
import com.analytics.realtime_analytics.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataService {
  private final List<User> users = new ArrayList<>();
  private final List<Post> posts = new ArrayList<>();

  public DataService() {
    init();
  }

  private void init() {
    Random rand = new Random();
    for (int i = 1; i <= 10; i++) {
      User user = new User("u" + i, "User " + i,
        "https://i.pravatar.cc/150?img=" + (i + 10), rand.nextInt(10) + 1);
      users.add(user);
      for (int j = 0; j < user.getPostCount(); j++) {
        Post post = new Post(UUID.randomUUID().toString(),
          "This is post #" + j + " by " + user.getName(),
          "https://source.unsplash.com/random/400x200?sig=" + rand.nextInt(1000),
          rand.nextInt(20), user);
        posts.add(post);
      }
    }
  }

  public List<User> getTopUsers() {
    return users.stream()
      .sorted((a, b) -> Integer.compare(b.getPostCount(), a.getPostCount()))
      .limit(5)
      .collect(Collectors.toList());
  }

  public List<Post> getTrendingPosts() {
    int maxComments = posts.stream().mapToInt(Post::getCommentCount).max().orElse(0);
    return posts.stream()
      .filter(p -> p.getCommentCount() == maxComments)
      .collect(Collectors.toList());
  }

  public List<Post> getAllPosts() {
    return posts.stream()
      .sorted((a, b) -> b.getId().compareTo(a.getId()))
      .collect(Collectors.toList());
  }
}
