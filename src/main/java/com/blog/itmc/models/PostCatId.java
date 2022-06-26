package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCatId implements Serializable {
    private long postId;   // FK: Id of class Member
    private String catId;    // FK: depart Id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCatId postCatId = (PostCatId) o;
        return postId==postCatId.getPostId() &&
                catId.equals(postCatId.getCatId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(postId, catId);
    }
}
