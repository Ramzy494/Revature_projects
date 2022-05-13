package project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="post_comments")
public class CommentModel {

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentId;
	
	@Column(name="comment_message", nullable=false, length = 140)
	private String commentMessage;
	

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="post_id", nullable=false)
	private PostModel post;


	// All but ID args constructor
	public CommentModel(String commentMessage, PostModel post) {
		super();
		this.commentMessage = commentMessage;

		this.post = post;

	}


	@Override
	public String toString() {
		return "CommentModel [commentId=" + commentId + ", commentMessage=" + commentMessage + ", post=" + post.getPostId() + "]";
	}
	
	
}
