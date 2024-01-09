package com.ll.likelionspringboottestmedium.domain.post.post.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<Post> search(boolean isPublished, String kw, Pageable pageable);

    Page<Post> search(Member author, String kw, Pageable pageable);
}
