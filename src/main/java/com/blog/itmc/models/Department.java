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

	public Collection<MemDepart> getMemDepart() {
		return memDepart;
	}

	public void setMemDepart(Collection<MemDepart> memDepart) {
		this.memDepart = memDepart;
	}
    
    
}
