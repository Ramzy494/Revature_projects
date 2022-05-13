package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.PostModel;
@Repository
public interface PostDao extends JpaRepository<PostModel, Integer>{

}
