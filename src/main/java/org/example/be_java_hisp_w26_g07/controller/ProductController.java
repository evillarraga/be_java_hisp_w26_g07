package org.example.be_java_hisp_w26_g07.controller;

import org.example.be_java_hisp_w26_g07.dto.PostRequestDto;
import org.example.be_java_hisp_w26_g07.service.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/post")
    public ResponseEntity<Void> addPost(@RequestBody PostRequestDto post) {
        return null;
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> getLatestPost(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return new ResponseEntity<>(productService.findProductByFollow(userId, order), HttpStatus.OK);
    }


}
