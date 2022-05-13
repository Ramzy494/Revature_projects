package project.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.model.CommentModel;
import project.service.CommentService;


@RestController
public class CommentController {
	static List<CommentModel>Commentlist= new ArrayList<>();
	
	
	
	 CommentService Service;
	@PostMapping(value="/AddComment")
 @ResponseBody
 public  void createComment(@RequestBody CommentModel comment) {
		Service.postComment(comment);
		
		
	
	}
	
		
	}
	
	


