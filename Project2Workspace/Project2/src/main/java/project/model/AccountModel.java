package project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="account")
public class AccountModel {
	
	// Columns
	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountId;
	
	@Column(name="account_username", unique=true, nullable=false)
	private String username;
	
	@Column(name="account_password", nullable=false)
	private String password;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<PostModel> posts = new ArrayList<PostModel>();

	// Needs a default profile picture URL later
	@Column(name="account_image", nullable=false)
	private String accountImage = "";

	
	// All but ID and image args constructor
	public AccountModel(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

}
