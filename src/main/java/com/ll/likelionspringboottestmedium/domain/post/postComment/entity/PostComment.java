package com.ll.likelionspringboottestmedium.domain.post.postComment.entity;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.entity.Post;
import com.ll.likelionspringboottestmedium.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
public class PostComment extends BaseEntity {
    @ManyToOne
    private Member author;
    @ManyToOne
    private Post post;
    @Column(columnDefinition = "TEXT")
    private String body;
}