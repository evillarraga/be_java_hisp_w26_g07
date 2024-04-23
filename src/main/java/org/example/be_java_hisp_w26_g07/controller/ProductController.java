package org.example.be_java_hisp_w26_g07.controller;

import org.example.be_java_hisp_w26_g07.dto.PostRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping("/post")
    public ResponseEntity<Void> addPost(@RequestBody PostRequestDto post) {
        return null;
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<Void> getLatestPost(@PathVariable String userId) {
        return null;
    }


}
