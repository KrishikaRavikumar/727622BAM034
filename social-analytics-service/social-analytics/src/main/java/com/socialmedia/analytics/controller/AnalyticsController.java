package com.socialmedia.analytics.controller;

// import com.socialmedia.analytics.model.Post;
import com.socialmedia.analytics.model.User;
import com.socialmedia.analytics.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getTopUsers() {
        return ResponseEntity.ok(analyticsService.getTopUsersByCommentCount());
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(@RequestParam("type") String type) {
        if ("popular".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analyticsService.getPopularPosts());
        } else if ("latest".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analyticsService.getLatestPosts());
        } else {
            return ResponseEntity.badRequest().body("Invalid type. Use 'popular' or 'latest'.");
        }
    }
}