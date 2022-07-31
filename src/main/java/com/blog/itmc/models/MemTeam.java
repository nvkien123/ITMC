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
@IdClass(MemTeamId.class)
public class MemTeam {
    @Id
    private int memberId;   // FK: Id of class Member
    @Id
    private String teamId;    // FK: team Id
	
    
}
