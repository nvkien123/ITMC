package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemDepartId  implements Serializable {
    private int memberId;   // FK: Id of class Member
    private String departId;    // FK: depart Id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemDepartId memDepartId = (MemDepartId) o;
        return departId.equals(memDepartId.getDepartId()) &&
                memberId == memDepartId.getMemberId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(memberId, departId);
    }
	
    
}
