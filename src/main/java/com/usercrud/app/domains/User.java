package com.usercrud.app.domains;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String fullName;
	@NotEmpty
	private String userName;
	@NotEmpty
	private String email;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String address;

	@Override
	public boolean equals(Object obj)   
	{  
	if (obj == null)   
	return false;  
	if (obj == this)  
	return true;  
	return this.getId() == ((User) obj). getId();  
	}  
	
}
