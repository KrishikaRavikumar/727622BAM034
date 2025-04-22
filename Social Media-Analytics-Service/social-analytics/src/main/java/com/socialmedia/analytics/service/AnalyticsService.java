package com.socialmedia.analytics.service;

import com.socialmedia.analytics.model.Comment;
import com.socialmedia.analytics.model.Post;
import com.socialmedia.analytics.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final String BASE_URL = "http://20.244.56.144/evaluation-service";
    private final RestTemplate restTemplate = new RestTemplate();

    private List<Post> latestPosts = new CopyOnWriteArrayList<>();
    private Map<Integer, Integer> userCommentCount = new ConcurrentHashMap<>();
    private Map<Integer, Post> popularPosts = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 300000) // Refresh every 5 mins
    public void refreshData() {
        latestPosts.clear();
        userCommentCount.clear();
        popularPosts.clear();

        Map<Integer, String> usersMap = getUsersFromAPI();
        for (Integer userId : usersMap.keySet()) {
            List<Post> posts = getPostsForUser(userId);
            for (Post post : posts) {
                latestPosts.add(post);
                List<Comment> comments = getCommentsForPost(post.getId());

                userCommentCount.merge(userId, comments.size(), Integer::sum);

                int currentMax = popularPosts.values().stream()
                        .mapToInt(p -> getCommentsForPost(p.getId()).size())
                        .max().orElse(0);

                if (comments.size() > currentMax) {
                    popularPosts.clear();
                    popularPosts.put(post.getId(), post);
                } else if (comments.size() == currentMax) {
                    popularPosts.put(post.getId(), post);
                }
            }
        }

        latestPosts = latestPosts.stream()
                .sorted(Comparator.comparingInt(Post::getId).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<User> getTopUsersByCommentCount() {
        Map<Integer, String> usersMap = getUsersFromAPI();
        return userCommentCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(5)
                .map(entry -> new User(entry.getKey(), usersMap.get(entry.getKey())))
                .collect(Collectors.toList());
    }

    public List<Post> getPopularPosts() {
        return new ArrayList<>(popularPosts.values());
    }

    public List<Post> getLatestPosts() {
        return latestPosts;
    }

    private Map<Integer, String> getUsersFromAPI() {
        String url = BASE_URL + "/users";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return (Map<Integer, String>) response.getBody().get("users");
    }

    private List<Post> getPostsForUser(int userId) {
        String url = BASE_URL + "/users/" + userId + "/posts";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> postsRaw = (List<Map<String, Object>>) response.getBody().get("posts");
        List<Post> posts = new ArrayList<>();
        for (Map<String, Object> entry : postsRaw) {
            posts.add(new Post((int) entry.get("id"), (int) entry.get("userid"), (String) entry.get("content")));
        }
        return posts;
    }

    private List<Comment> getCommentsForPost(int postId) {
        String url = BASE_URL + "/posts/" + postId + "/comments";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            List<Map<String, Object>> raw = (List<Map<String, Object>>) response.getBody().get("comments");
            List<Comment> comments = new ArrayList<>();
            for (Map<String, Object> entry : raw) {
                comments.add(new Comment((int) entry.get("id"), (int) entry.get("postid"), (String) entry.get("content")));
            }
            return comments;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}