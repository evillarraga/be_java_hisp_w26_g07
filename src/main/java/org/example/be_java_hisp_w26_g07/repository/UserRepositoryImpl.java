package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.entity.*;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final Map<Integer, User> users;
    private final List<Followers> followersList;
    private final Map<Integer, Category> categories;
    private final Map<Integer, Post> posts;
    private final Map<Integer, Product> products;

    public UserRepositoryImpl(
            @Qualifier("getInitialUsers") HashMap<Integer, User> users,
            @Qualifier("getInitialFollowers") ArrayList<Followers> followers,
            @Qualifier("getInitialCategories") HashMap<Integer, Category> categories,
            @Qualifier("getInitialPosts") HashMap<Integer, Post> posts,
            @Qualifier("getInitialProducts") HashMap<Integer, Product> products
    ) {
        this.users = users;
        this.followersList = followers;
        this.categories = categories;
        this.products = products;
        this.posts = posts;
    }

    @Override
    public User findById(Integer id) {
        return users.get(id);
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    /**
     * validacion de si un usuario ya sigue un vendedor
     *
     * @param id           identificador del usuario
     * @param userToFollow identificador del vendedor
     */
    @Override
    public Boolean userFollowSeller(Integer id, Integer userToFollow) {
        Followers foundFollower = followersList.stream()
                .filter(f -> f.getUserId().equals(id) && f.getSellerId().equals(userToFollow))
                .findAny()
                .orElse(null);

        return foundFollower != null;
    }

    /**
     * funcion usada para que un usuario pueda seguir un vendedor
     *
     * @param id           identificador del usuario
     * @param userToFollow identificador del vendedor
     */
    @Override
    public Boolean addFollowerById(Integer id, Integer userToFollow) {
        Followers newFollow = new Followers(id, userToFollow);
        return followersList.add(newFollow);
    }

    @Override
    public List<Integer> followerIdBySellerId(Integer sellerId) {
        return followersList.stream()
                .filter(f -> f.getSellerId().equals(sellerId))
                .mapToInt(Followers::getSellerId)
                .boxed().toList();
    }

    @Override
    public List<Integer> followedIdByUserId(Integer userId) {
        return followersList.stream()
                .filter(f -> f.getUserId().equals(userId))
                .mapToInt(Followers::getSellerId)
                .boxed().toList();
    }

    @Override
    public List<Post> findProductByFollow(User user) {
        List<Post> posts = new ArrayList<>();
        List<Integer> sellerIdList = followedIdByUserId(user.getId());

        List<Post> postsRecently;
        for (Integer sellerId : sellerIdList) {
            postsRecently = getPostsBySellerId(sellerId).stream()
                    .filter(post -> post.getDate().isAfter(LocalDate.now().minusDays(14)))
                    .toList();
            posts.addAll(postsRecently);
        }
        return posts;
    }

    @Override
    public Product findProductById(Integer productId) {
        return products.get(productId);
    }

    @Override
    public void createProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categories.get(categoryId);
    }

    @Override
    public boolean unfollow(User user, User sellerUser) {
        Followers userToRemove = new Followers(user.getId(), sellerUser.getId());
        return followersList.remove(userToRemove);
    }

    @Override
    public void addPost(Post newPost) {
        posts.put(newPost.getId(), newPost);
    }

    @Override
    public List<Post> getPostsBySellerId(Integer userId) {
        return posts.values().stream()
                .filter(p -> p.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
