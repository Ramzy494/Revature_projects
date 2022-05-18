package project.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.CommentDao;
import project.dao.PostDao;
import project.model.AccountModel;
import project.model.CommentModel;

@Service

public class CommentService {
	CommentDao Dao;
	PostDao p;
	ProfanityFilter F;

	@Autowired
	public CommentService(CommentDao dao, PostDao p, ProfanityFilter f) {
		super();
		Dao = dao;
		this.p = p;
		F = f;
	}

	/**
	 * Service layer method that adds comments to posts
	 * 
	 * @param Comment
	 * @param session
	 * @return
	 */
	public CommentModel postComment(CommentModel Comment, HttpSession session) {
		Comment.setCommentMessage(F.getCensoredText(Comment.getCommentMessage()));
		Comment.setAccount((AccountModel) session.getAttribute("currentUser"));
		return Dao.save(Comment);
	}

	/**
	 * Service layer method that creates a list of comments based on the post id
	 * 
	 * @param ID
	 * @return
	 */
	public List<CommentModel> GetCommentByPost(int ID) {
		return fetchCommentUserInfo(Dao.findAllByPostPostId(ID));
	}

	/**
	 * Service layer method that creates a list of comments that sets the username
	 * and account image to a specific comment
	 * 
	 * @param comments
	 * @return
	 */
	public List<CommentModel> fetchCommentUserInfo(List<CommentModel> comments) {
		for (CommentModel c : comments) {
			c.setUsername(c.getAccount().getUsername());
			c.setAccountImage(c.getAccount().getAccountImage());
		}
		return comments;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public CommentModel fetchCommentUserInfo(CommentModel c) {
		c.setUsername(c.getAccount().getUsername());
		c.setAccountImage(c.getAccount().getAccountImage());
		return c;
	}

	public CommentModel GetCommentByID(int ID) {
		return Dao.getById(ID);
	}

}
