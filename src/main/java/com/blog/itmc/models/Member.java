package com.blog.itmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;   
    private String studentId;
    private String fullName;// icon url
    @Column(name = "phone", length = 10, nullable = false)
    @Pattern(regexp = "0\\d{9}", message = "So dien thoai khong hop le!")
    private String phone;
    private int positionId; // FK: position id
    
}
