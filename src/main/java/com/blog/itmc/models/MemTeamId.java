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
    private int memberId;   // FK: Id of class Member
    private String teamId;    // FK: depart Id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemTeamId memTeamId = (MemTeamId) o;
        return teamId.equals(memTeamId.getTeamId()) &&
                memberId ==memTeamId.getMemberId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(memberId, teamId);
    }
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
    
}
