package com.ll.likelionspringboottestmedium.domain.post.postLike.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.entity.Post;
import com.ll.likelionspringboottestmedium.domain.post.postLike.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByPostInAndMember(List<Post> posts, Member member);
}
