package org.example.be_java_hisp_w26_g07.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.be_java_hisp_w26_g07.dto.PostDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements IProductService {

    IUserRepository iUserRepository;

    public ProductImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<PostDto> findProductByFollow(Integer userID, String order) {
        ObjectMapper mapper = new ObjectMapper();
        List<Post> postsList = iUserRepository.findProductByFollow(iUserRepository.findById(userID));

        if (postsList.isEmpty()) {
            throw new NotFoundException("No se encontraron publicaciones para las ultimas dos semanas.");
        }
        getPostOrderByDate(postsList, order);
        return postsList.stream()
                .map(post -> mapper.convertValue(post, PostDto.class))
                .collect(Collectors.toList());
    }

    private void getPostOrderByDate(List<Post> postList,
                                    String order) {
        if (order == null) return;

        switch (order) {
            case "date_asc":
                postList.sort(Comparator.comparing(Post::getDate));
                break;
            case "date_desc":
                postList.sort(Comparator.comparing(Post::getDate).reversed());
                break;
            default:
        }

    }
}
