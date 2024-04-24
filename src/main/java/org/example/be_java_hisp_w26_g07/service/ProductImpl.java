package org.example.be_java_hisp_w26_g07.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.be_java_hisp_w26_g07.dto.PostDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.ProductRepositoryImpl;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IProductRespository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements IProductService {

    IProductRespository iProductRespository;

    public ProductImpl(ProductRepositoryImpl iProductRespository){
        this.iProductRespository = iProductRespository;
    }

    @Override
    public List<PostDto> findProductByFollow(Integer userID) {
        ObjectMapper mapper = new ObjectMapper();
        List<Post> postsList = iProductRespository.findProductByFollow(userID);
        if (postsList.isEmpty()){
            throw new NotFoundException("No se encontraron publicaciones para las ultimas dos semanas.");
        }
        return postsList.stream()
                .map(post -> mapper.convertValue(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
