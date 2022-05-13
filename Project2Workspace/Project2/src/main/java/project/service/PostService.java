package project.service;

import project.dao.AccountDao;
import project.dao.PostDao;
import project.model.AccountModel;
import project.model.PostModel;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PostService {
	PostDao p;
	AccountDao DAO;
	
	@Autowired
	public PostService(PostDao p,AccountDao F) {
		super();
		this.p = p;
		this.DAO= F;
	}
	
	

	public void postPost(PostModel post, HttpSession Session) {
		
		post.setAccount(DAO.findByUsername("Ramzy"));
		//(AccountModel)Session.getAttribute("currentUser")
		//System.out.println(post.getAccount());
		p.save(post);
		
		
	}
	
public  List<PostModel> Readall() {
	return p.findAll();
	
}
}
