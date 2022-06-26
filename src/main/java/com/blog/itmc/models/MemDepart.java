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
@IdClass(MemDepartId.class)
public class MemDepart {
    @Id
    private String studentId;   // FK: Id of class Member
    @Id
    private String departId;    // FK: depart Id
}
