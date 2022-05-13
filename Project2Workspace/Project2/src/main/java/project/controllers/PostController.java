package project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

	@PostMapping(value="/AddPost")
	 @ResponseBody
	 public  void createPost(@RequestBody PostModel post, HttpSession Session) { 
		
		
			Service.postPost(post,Session);
				
		}
	@PostMapping(value="/ReadPost")
	public  List<PostModel> readPost() {
		return Service.Readall();
	}
		

}
