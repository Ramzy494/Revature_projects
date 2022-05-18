package project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.AccountModel;
import project.model.CommentModel;
@Repository
public interface CommentDao extends JpaRepository<CommentModel, Integer>{

	List<CommentModel> findAllByPostPostId(int Id);	
	
	
}
