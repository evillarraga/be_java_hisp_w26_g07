package org.example.be_java_hisp_w26_g07.controller;

import org.example.be_java_hisp_w26_g07.dto.SuccessResponseDto;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

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

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccessResponseDto> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        SuccessResponseDto responseDto = userService.unfollow(userId, userIdToUnfollow);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
