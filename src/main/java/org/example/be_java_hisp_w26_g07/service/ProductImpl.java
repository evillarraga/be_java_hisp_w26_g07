package org.example.be_java_hisp_w26_g07.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.be_java_hisp_w26_g07.dto.products.PostDto;
import org.example.be_java_hisp_w26_g07.dto.products.PostRequestDto;
import org.example.be_java_hisp_w26_g07.dto.products.ProductDto;
import org.example.be_java_hisp_w26_g07.dto.products.SimplePostDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.Product;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IProductService;

import org.example.be_java_hisp_w26_g07.utils.PostUtil;
import org.example.be_java_hisp_w26_g07.utils.UserMessageError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements IProductService {

    private final IUserRepository iUserRepository;

    public ProductImpl(@Autowired IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<PostDto> findProductByFollow(Integer userID, String order) {
        ObjectMapper mapper = new ObjectMapper();

        User foundUser = iUserRepository.findById(userID);
        if (foundUser == null) {
            throw new BadRequestException("El usuario con id "+userID+" no existe");
        }

        List<Post> postsList = iUserRepository.findProductByFollow(foundUser);
        if (postsList.isEmpty()) {
            throw new NotFoundException("No se encontraron publicaciones para las ultimas dos semanas.");
        }

        return PostUtil.getPostOrderByDate(postsList, order).stream()
                .map(post -> {
                    PostDto mappedPostDto = mapper.convertValue(post, PostDto.class);
                    Product product = iUserRepository.findProductById(post.getProductId());
                    ProductDto mappedProductDto = mapper.convertValue(product, ProductDto.class);
                    mappedPostDto.setProduct(mappedProductDto);
                    return mappedPostDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostRequestDto postRequestDto) {
        User myUser = iUserRepository.findById(postRequestDto.getUserId());
        if (myUser == null) {
            throw new BadRequestException(UserMessageError.CLIENT_NOT_FOUND.getMessage(postRequestDto.getUserId()));
        }

        ObjectMapper mapper = new ObjectMapper();
        SimplePostDto simplePostDto = mapper.convertValue(postRequestDto, SimplePostDto.class);
        Post post = mapper.convertValue(simplePostDto, Post.class);
        post.setId(PostUtil.increaseCounter());

        Product product = mapper.convertValue(postRequestDto.getProduct(), Product.class);
        Product foundProduct = iUserRepository.findProductById(product.getId());
        if (foundProduct != null) {
            throw new BadRequestException("Un producto con ese id ya existe, por favor actualice el id");
        }

        iUserRepository.createProduct(product);
        post.setProductId(product.getId());

        iUserRepository.addPost(post);
        if (!myUser.getIsSeller()) myUser.setIsSeller(true);

        PostDto createdPost = mapper.convertValue(post, PostDto.class);
        ProductDto createdProduct = mapper.convertValue(product, ProductDto.class);
        createdPost.setProduct(createdProduct);
        return createdPost;
    }
}
