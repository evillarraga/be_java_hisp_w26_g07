package org.example.be_java_hisp_w26_g07.controller;

import org.example.be_java_hisp_w26_g07.dto.FollowedResponseDto;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(@Autowired IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userService.userFollowSeller(userId, userIdToFollow), HttpStatus.NO_CONTENT);
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
    public ResponseEntity<FollowedResponseDto> followersList(@PathVariable Integer userId,
                                                             @RequestParam String order) {
        return new ResponseEntity<>(userService.findFollowedUsers(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollow(@PathVariable String userId, @PathVariable String userIdToUnfollow) {
        return null;
    }


}
