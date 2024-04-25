package org.example.be_java_hisp_w26_g07.service.interfaces;

import org.example.be_java_hisp_w26_g07.dto.PostDto;
import org.example.be_java_hisp_w26_g07.dto.PostRequestDto;
import org.example.be_java_hisp_w26_g07.dto.PromoPostCountDto;
import org.example.be_java_hisp_w26_g07.dto.PromotionPostDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    List<PostDto> findProductByFollow(Integer userID, String order);

    PostDto createPost(PostRequestDto postRequestDto);

    PostDto createPromotionPost(PromotionPostDto promotion);

    PromoPostCountDto findListPromotionCountById(Integer userId);

    List<PostDto> findListPromotionById(Integer userId);

    List<PostDto> findProductsBetweenPrice(Double minPrice, Double maxPrice);
}
