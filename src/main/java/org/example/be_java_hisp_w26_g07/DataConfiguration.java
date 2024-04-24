package org.example.be_java_hisp_w26_g07;

import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.Product;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DataConfiguration {

    @Bean
    public List<User> getUsers() {
        LocalDate date1 = LocalDate.of(2023, 11, 15);
        LocalDate date2 = LocalDate.of(2023, 11, 16);
        LocalDate date3 = LocalDate.of(2023, 12, 17);
        LocalDate date4 = LocalDate.of(2023, 11, 18);
        LocalDate date5 = LocalDate.of(2024, 03, 03);
        LocalDate date6 = LocalDate.of(2024, 04, 04);
        LocalDate date7 = LocalDate.of(2024, 04, 21);
        LocalDate date8 = LocalDate.of(2024, 04, 22);
        LocalDate date9 = LocalDate.of(2023, 11, 23);
        LocalDate date10 = LocalDate.of(2024, 04, 24);

        Post post1 = new Post(1, 1, date1, new Product(1, "Chair", "Furniture", "Furniture Co.", "Brown", "Comfortable chair for home or office", 1), "Furniture", 99.99);
        Post post2 = new Post(1, 2, date2, new Product(2, "Desk", "Furniture", "Furniture Co.", "Black", "Sturdy desk for work or study", 2), "Furniture", 199.99);
        Post post3 = new Post(1, 3, date3, new Product(3, "Sofa", "Furniture", "Furniture Co.", "Gray", "Modern sofa for living room", 3), "Furniture", 499.99);
        Post post4 = new Post(1, 4, date4, new Product(4, "Lamp", "Decor", "Decor Store", "White", "Elegant lamp for home decor", 4), "Decor", 29.99);
        Post post5 = new Post(2, 5, date5, new Product(5, "Table", "Furniture", "Furniture Co.", "Oak", "Solid wood table for dining", 5), "Furniture", 299.99);
        Post post6 = new Post(2, 6, date6, new Product(6, "TV Stand", "Furniture", "Furniture Co.", "Walnut", "TV stand with storage shelves", 6), "Furniture", 199.99);
        Post post7 = new Post(3, 7, date7, new Product(7, "Bookshelf", "Furniture", "Furniture Co.", "Cherry", "Classic bookshelf for home library", 7), "Furniture", 149.99);
        Post post8 = new Post(4, 8, date8, new Product(8, "Mirror", "Decor", "Decor Store", "Silver", "Decorative mirror for wall", 8), "Decor", 49.99);
        Post post9 = new Post(4, 9, date9, new Product(9, "Bed", "Furniture", "Furniture Co.", "Gray", "Comfortable bed for a good night's sleep", 9), "Furniture", 699.99);
        Post post10 = new Post(4, 10, date10, new Product(10, "Chair", "Furniture", "Furniture Co.", "Red", "Modern chair with metal legs", 10), "Furniture", 79.99);

        Map<Integer, Post> map1 = new HashMap<>();
        Map<Integer, Post> map2 = new HashMap<>();
        Map<Integer, Post> map3 = new HashMap<>();
        Map<Integer, Post> map4 = new HashMap<>();

        map1.put(1, post1);
        map1.put(2, post2);
        map1.put(3, post3);
        map1.put(4, post4);
        map2.put(5, post5);
        map2.put(6, post6);
        map3.put(7, post7);
        map4.put(8, post8);
        map4.put(9, post9);
        map4.put(10, post10);

        List<User> users = new ArrayList<>();

        users.add(new User(1, "Monica", map1, List.of(2, 3, 4, 8, 9), List.of(2, 3), true));
        users.add(new User(2, "Santiago", map2, List.of(1, 3, 5, 6, 9), List.of(1, 3), true));
        users.add(new User(3, "Cristian", map3, List.of(1, 2, 5, 6, 9), List.of(1, 2, 4), true));
        users.add(new User(4, "Edwin", map4, List.of(3, 7, 8), List.of(1), true));
        users.add(new User(5, "Bryann", new HashMap<>(), new ArrayList<>(), List.of(2, 3), false));
        users.add(new User(6, "Carlos", new HashMap<>(), new ArrayList<>(), List.of(2, 3), false));
        users.add(new User(7, "Cristopher", new HashMap<>(), new ArrayList<>(), List.of(4), false));
        users.add(new User(8, "Leandro", new HashMap<>(), new ArrayList<>(), List.of(1, 4), false));
        users.add(new User(9, "Martin", new HashMap<>(), new ArrayList<>(), List.of(1, 2, 3), false));

        return users;
    }
}
