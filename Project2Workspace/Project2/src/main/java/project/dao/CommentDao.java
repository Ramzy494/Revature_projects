package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.CommentModel;
@Repository
public interface CommentDao extends JpaRepository<CommentModel, Integer>{

	
	
}
