package project.service;


import project.dao.CommentDao;
import project.model.CommentModel;



import org.springframework.stereotype.Service;

@Service

public class CommentService {
CommentDao Dao;	
	public void postComment(CommentModel Comment) {
		Dao.save(Comment);
		
	}

}
