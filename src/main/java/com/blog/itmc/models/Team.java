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
public class Team {
    @Id
    private String name;
    private String icon;// icon url
    private String description;
    private String depart;// FK: depart name
    
    @OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL)
    private Collection<MemTeam> memTeam;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Collection<MemTeam> getMemTeam() {
		return memTeam;
	}

	public void setMemTeam(Collection<MemTeam> memTeam) {
		this.memTeam = memTeam;
	}
    
    
}
