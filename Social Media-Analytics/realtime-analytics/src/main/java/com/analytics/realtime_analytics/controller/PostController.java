package com.analytics.realtime_analytics.controller;

import com.analytics.realtime_analytics.model.Post;
import com.analytics.realtime_analytics.model.User;
import com.analytics.realtime_analytics.service.DataService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

  private final DataService service;

  public PostController(DataService service) {
    this.service = service;
  }

  @GetMapping("/top-users")
  public List<User> getTopUsers() {
    return service.getTopUsers();
  }

  @GetMapping("/trending-posts")
  public List<Post> getTrendingPosts() {
    return service.getTrendingPosts();
  }

  @GetMapping("/posts")
  public List<Post> getPosts() {
    return service.getAllPosts();
  }
}

