package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.Product;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IProductRespository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRespository {

    List<User> usersDB= new ArrayList<>();

    @Override
    public List<Post> findProductByFollow(Integer userId) {
        List<Post> posts = new ArrayList<>();
        List<Post> postsRecently= new ArrayList<>();
        for (User user: usersDB) {
            if (user.getFollowerIds().contains(userId)) {
                postsRecently = user.getPosts().stream()
                        .filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(2)))
                        .collect(Collectors.toList());
            }
            postsRecently.addAll(posts);
        }
        return posts;
    }

}
