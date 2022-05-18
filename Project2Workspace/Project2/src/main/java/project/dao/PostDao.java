package project.dao;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import project.model.PostModel;

@Repository
public interface PostDao extends JpaRepository<PostModel, Integer>{

	List<PostModel> findByPostIdBetween(int postIdStart, int postIdEnd);

	PostModel findByPostId(int id);

	List<PostModel> findByPostIdBetweenOrderByPostIdDesc(int postIdStart, int i);

	List<PostModel> findAllByAccountAccountIdOrderByPostIdDesc(int id);

}
