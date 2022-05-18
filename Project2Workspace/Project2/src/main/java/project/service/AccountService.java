package project.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import project.dao.AccountDao;
import project.model.AccountModel;

@Service
@NoArgsConstructor
public class AccountService {

	private AccountDao dao;
	HttpSession sess;
	Pbkdf2PasswordEncoder encoder;

	@Autowired
	public AccountService(AccountDao dao, Pbkdf2PasswordEncoder encoder, HttpSession sess) {
		super();
		this.dao = dao;
		this.encoder = encoder;
		this.sess = sess;

	}

	/**
	 * Service layer method that retrieves an account based on the username from the
	 * dao layer
	 * 
	 * @param user
	 * @return
	 */
	public AccountModel getAccountByUsername(String user) {
		return dao.findByUsername(user);
	}
/**
 * Service layer method that creates a new Account model using the form data from the controller and then saves to the database using the Dao layer
 * @param username
 * @param password
 * @param firstName
 * @param lastName
 * @param email
 */
	public void registerAccount(String username, String password, String firstName, String lastName, String email) {

		String securePassword = encoder.encode(password);

		AccountModel newUser = new AccountModel(username, securePassword, firstName, lastName, email);

		dao.save(newUser);
		sess.setAttribute("currentUser", newUser);

	}

	/**
	 * Service layer method that takes in the email, a tempPassword and the new password in order to update the password for an account accordingly
	 * @param email
	 * @param tempPassword
	 * @param password
	 */
	public void updatePassword(String email, String tempPassword, String password) {

		AccountModel resetPasswordUser = dao.findByEmail(email);
		String encodedPassword = resetPasswordUser.getPassword();

		if (encoder.matches(tempPassword, encodedPassword)) {
			String securePassword = encoder.encode(password);

			resetPasswordUser.setPassword(securePassword);
			dao.save(resetPasswordUser);
		} else {
			System.out.println("Reseting password failed");
		}

	}
/**
 * Service layer method that gets an account by id and then saves a profile picture to the account
 * @param id
 * @param url
 */
	public void updateImage(int id, String url) {
		AccountModel account = dao.getById(id);
		account.setAccountImage(url);
		dao.save(account);
	}
/**
 * Service layer method that updates a profile's information from an AJAX request
 * @param User
 * @param currentUser
 * @return
 */
	public AccountModel updateProfile(AccountModel User, AccountModel currentUser) {

		System.out.println(User);
		if (!(User.getFirstName() == "")) {
			System.out.println("changing fn");
			currentUser.setFirstName((User.getFirstName()));

		}

		if (!(User.getLastName() == "")) {
			System.out.println("changing ln");
			currentUser.setLastName((User.getLastName()));

		}

		dao.save(currentUser);
		return currentUser;
	}
/**
 * Service layer method that updates a profile's bending style based on the button selected on their home page
 * @param bendingStyle
 */
	public void updateBending(String bendingStyle) {

		AccountModel addBending = (AccountModel) sess.getAttribute("currentUser");
		addBending.setBendingType(bendingStyle);
		System.out.println(addBending);

		dao.save(addBending);

	}

}
