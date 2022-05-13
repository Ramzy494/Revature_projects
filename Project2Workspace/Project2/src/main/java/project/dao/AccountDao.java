package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.AccountModel;

@Repository
public interface AccountDao extends JpaRepository<AccountModel, Integer>{

	public AccountModel findByUsername(String user);
	// This essentially gets all account info based on the email and stores/returns it as an object
	// In Sql this read SELECT * FROM account WHERE account_email = email;
	public AccountModel findByEmail(String email);
	
}
