package project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.model.AccountModel;
import project.model.PostModel;
import project.service.PostService;

@RestController
public class PostController {
	PostService Service;

	@Autowired
	public PostController(PostService service) {
		super();
		Service = service;
	}

	/**
	 * Controller that adds a post based on a model sent from AJAX and the user from the session
	 * @param post
	 * @param Session
	 * @return
	 */
	@PostMapping(value = "/AddPost")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public PostModel createPost(@RequestBody PostModel post, HttpSession Session) {
		return Service.postPost(post, Session);
	}

	/**
	 * Controller that calls for a list of all posts
	 * @return
	 */
	@PostMapping(value = "/ReadPost")
	public List<PostModel> readPost() {
		return Service.Readall();
	}

	/**
	 * Controller that calls for a list of all posts by current user
	 * @param Session
	 * @return
	 */
	@GetMapping(value = "/GetPostByCurrentUser")
	@ResponseBody
	public List<PostModel> GetPostByCurrentUser(HttpSession Session) {
		AccountModel H= (AccountModel) Session.getAttribute("currentUser");
		return Service.getPostsByUser(H.getAccountId());

	}
	/**
	 * Controller that calls for a list of all posts by a given user
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/GetPostsByUser/{id}")
	public List<PostModel> GetPostsByUser(@PathVariable(name = "id") Integer id) {
		return Service.getPostsByUser(id);
	}
	
	/**
	 * Controller that will add a like to a given post based on its ID
	 * @param id
	 * @return
	 */
	@PostMapping("/likePostById/{id}")
	public PostModel likeById(@PathVariable(name = "id") Integer id) {
		return Service.LikePost(id);
	}
	/**
	 * Controller that returns the total amount of posts in the database
	 * @return
	 */
	@GetMapping(value = "/getPostCount")
	public long getPostCount() {
		return Service.getPostCount();
	}
	/**
	 * Controller that gives a list of posts based on the pagination value from the front end.
	 * @param page
	 * @return
	 */
	@GetMapping(value="/getPostsByPage/{page}")
	public List<PostModel> getPostsByPage(@PathVariable(name="page") Integer page) {
		return Service.getPostsByPage(page);
	}
	
}
