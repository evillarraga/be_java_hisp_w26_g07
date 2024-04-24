package org.example.be_java_hisp_w26_g07.repository.interfaces;

import org.example.be_java_hisp_w26_g07.entity.Post;

import javax.naming.InterruptedNamingException;
import java.util.List;

public interface IProductRespository {
    List<Post> findProductByFollow(Integer userId);
}
