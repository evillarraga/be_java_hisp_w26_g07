package org.example.be_java_hisp_w26_g07.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> follow(@PathVariable String userId, @PathVariable String userIdToFollow) {
        return null;
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<Void> numberOfSellersFollowed(@PathVariable String userId) {
        return null;
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<Void> listOfFollowedSellesrs(@PathVariable String userId) {
        return null;
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<Void> followersList(@PathVariable String userId) {
        return null;
    }

    @PostMapping("/{userId}/follow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollow(@PathVariable String userId, @PathVariable String userIdToUnfollow) {
        return null;
    }

    
}
