package org.example.be_java_hisp_w26_g07;

import org.example.be_java_hisp_w26_g07.entity.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@TestConfiguration
public class DataConfiguration {

    @Bean
    public HashMap<Integer, User> getInitialUsers() {
        List<User> userList = new ArrayList<>();

        userList.add(new User(1, "Monica Test", true));
        userList.add(new User(2, "Santiago", true));
        userList.add(new User(3, "Cristian", true));
        userList.add(new User(4, "Edwin", true));
        userList.add(new User(5, "Bryann", false));
        userList.add(new User(6, "Carlos", false));
        userList.add(new User(7, "Cristopher", false));
        userList.add(new User(8, "Leandro", false));
        userList.add(new User(9, "Martin", false));
        userList.add(new User(10, "Pepito", true));

        HashMap<Integer, User> usersHashMap = new HashMap<>();

        for (User user : userList) {
            usersHashMap.put(user.getId(), user);
        }
        return usersHashMap;
    }

    @Bean
    public ArrayList<Followers> getInitialFollowers() {
        return new ArrayList<>(List.of(
                new Followers(1, 2),
                new Followers(1, 3),
                new Followers(1, 4),
                new Followers(2, 1), //Sigue a monica
                new Followers(2, 3),
                new Followers(3, 1),  //Sigue a monica
                new Followers(3, 2),
                new Followers(3, 4),
                new Followers(4, 1), //Sigue a monica
                new Followers(5, 2),
                new Followers(5, 3),
                new Followers(6, 2),
                new Followers(6, 3),
                new Followers(7, 4),
                new Followers(8, 1), //Sigue a monica
                new Followers(8, 4),
                new Followers(9, 1), //Sigue a monica
                new Followers(9, 2),
                new Followers(9, 3)
        ));
    }

    @Bean
    HashMap<Integer, Category> getInitialCategories() {
        List<Category> categoryList = List.of(
                new Category(1, "Furniture"),
                new Category(2, "Decor")
        );
        HashMap<Integer, Category> categoryHashMap = new HashMap<>();

        for (Category category : categoryList) {
            categoryHashMap.put(category.getId(), category);
        }
        return categoryHashMap;
    }

    @Bean
    HashMap<Integer, Product> getInitialProducts() {
        List<Product> productList = List.of(
                new Product(1, "Chair", "Furniture", "Furniture Co.", "Brown", "Comfortable chair for home or office"),
                new Product(2, "Desk", "Furniture", "Furniture Co.", "Black", "Sturdy desk for work or study"),
                new Product(3, "Sofa", "Furniture", "Furniture Co.", "Gray", "Modern sofa for living room"),
                new Product(4, "Lamp", "Decor", "Decor Store", "White", "Elegant lamp for home decor"),
                new Product(5, "Table", "Furniture", "Furniture Co.", "Oak", "Solid wood table for dining"),
                new Product(6, "TV Stand", "Furniture", "Furniture Co.", "Walnut", "TV stand with storage shelves"),
                new Product(7, "Bookshelf", "Furniture", "Furniture Co.", "Cherry", "Classic bookshelf for home library"),
                new Product(8, "Mirror", "Decor", "Decor Store", "Silver", "Decorative mirror for wall"),
                new Product(9, "Bed", "Furniture", "Furniture Co.", "Gray", "Comfortable bed for a good night's sleep"),
                new Product(10, "Chair", "Furniture", "Furniture Co.", "Red", "Modern chair with metal legs")
        );
        HashMap<Integer, Product> productHashMap = new HashMap<>();

        for (Product product : productList) {
            productHashMap.put(product.getId(), product);
        }
        return productHashMap;
    }

    @Bean
    HashMap<Integer, Post> getInitialPosts() {
        LocalDate date1 = LocalDate.of(2023, 11, 15);
        LocalDate date2 = LocalDate.of(2023, 11, 16);
        LocalDate date3 = LocalDate.of(2023, 12, 17);
        LocalDate date4 = LocalDate.of(2023, 11, 18);
        LocalDate date5 = LocalDate.of(2024, 3, 3);
        LocalDate date6 = LocalDate.of(2024, 4, 4);
        LocalDate date7 = LocalDate.of(2024, 4, 21);
        LocalDate date8 = LocalDate.of(2024, 4, 22);
        LocalDate date9 = LocalDate.of(2023, 11, 23);
        LocalDate date10 = LocalDate.of(2024, 4, 24);

        List<Post> postList = List.of(
                new Post(1, 1, date1, 1, 1, 99.99),
                new Post(1, 2, date2, 2, 1, 199.99),
                new Post(1, 3, date3, 3, 1, 499.99),
                new Post(1, 4, date4, 4, 2, 29.99),
                new Post(2, 5, date5, 5, 1, 299.99),
                new Post(2, 6, date6, 6, 1, 199.99),
                new Post(3, 7, date7, 7, 1, 149.99),
                new Post(4, 8, date8, 8, 2, 49.99),
                new Post(4, 9, date9, 9, 1, 699.99),
                new Post(4, 10, date10, 10, 1, 79.99)
        );

        HashMap<Integer, Post> postHashMap = new HashMap<>();

        for (Post post : postList) {
            postHashMap.put(post.getId(), post);
        }
        return postHashMap;
    }
}