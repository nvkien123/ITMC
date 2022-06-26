package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemTeamId implements Serializable {
    private String studentId;   // FK: Id of class Member
    private String teamId;    // FK: depart Id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemTeamId memTeamId = (MemTeamId) o;
        return teamId.equals(memTeamId.getTeamId()) &&
                studentId.equals(memTeamId.getStudentId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(studentId, teamId);
    }
}
