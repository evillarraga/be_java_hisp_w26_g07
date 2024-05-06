package org.example.be_java_hisp_w26_g07.repository.interfaces;

import org.example.be_java_hisp_w26_g07.entity.Category;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.Product;
import org.example.be_java_hisp_w26_g07.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();

    User findById(Integer id);

    Boolean addFollowerById(Integer id, Integer userToFollow);
    List<Integer> followerIdBySellerId(Integer sellerId);
    List<Integer> followedIdByUserId(Integer userId);
    Boolean userFollowSeller(Integer id, Integer userToFollow);
    boolean unfollow(User user, User sellerUser);
    List<Post> findProductByFollow(User user);
    Product findProductById(Integer productId);
    void createProduct(Product newProduct);
    Category getCategoryById(Integer categoryId);
    public void addPost(Post newPost);
    List<Post> getPostsBySellerId(Integer userId);
}
