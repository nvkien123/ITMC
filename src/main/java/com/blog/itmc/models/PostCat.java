package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PostCatId.class)
public class PostCat {
    @Id
    private String postId;   // FK: Id of class Member
    @Id
    private String catId;    // FK: depart Id
}
