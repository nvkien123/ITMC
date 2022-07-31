package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    private String name;
    private String icon;// icon url
    private String description;
    
    @OneToMany(mappedBy = "departId", cascade = CascadeType.ALL)
    private Collection<MemDepart> memDepart;
    
    @OneToMany(mappedBy = "depart", cascade = CascadeType.ALL)
    private Collection<Team> teams;
    
    
}
