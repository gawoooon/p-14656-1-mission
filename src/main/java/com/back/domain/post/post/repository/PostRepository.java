package com.back.domain.post.post.repository;

import com.back.domain.post.post.document.Post;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
    List<Post> findAll();
}
