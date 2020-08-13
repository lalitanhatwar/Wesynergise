package com.capgemini.userDashBoard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author lhatwar
 *
 */
@Entity
@Table(name = "MyUser")
@Data
public class MyUser {
	
	@Id
	@GeneratedValue
	private Long userId;
	
	@NotEmpty(message = "{userName.notempty}")
//	@Max(20)
//	@Min(1)
	private String userName;
	
	@NotEmpty(message = "{shortname.notempty}")
	private String shortname;
	
	@NotEmpty(message = "{email.notempty}")
	@Email
	private String email;
	
	@NotEmpty(message = "{roleCategory.notempty}")
	private String roleCategory;
	
	@NotEmpty(message = "{applicationName.notempty}")
	private String applicationName;
	
	@NotEmpty(message = "{assignmentGroup.notempty}")
	private String assignmentGroup;
	
	public MyUser() {
		
	}

	public MyUser(Long userId, 
			 String userName,
			 String shortname,
			 String email,
			 String roleCategory,
			 String applicationName,
			 String assignmentGroup) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.shortname = shortname;
		this.email = email;
		this.roleCategory = roleCategory;
		this.applicationName = applicationName;
		this.assignmentGroup = assignmentGroup;
	}
	
	
	
	
}
