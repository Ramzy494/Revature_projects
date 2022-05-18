package project.model;

import java.io.Serializable;

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

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="post_id", nullable=false)
	@JsonBackReference(value="post-comment")
	private PostModel post;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinColumn(name="account_id", nullable=false)
	@JsonBackReference(value="account-comment")
	private AccountModel account;
	
	

	// All but ID args constructor
	public CommentModel(String commentMessage, PostModel post, AccountModel account) {
		super();
		this.commentMessage = commentMessage;
		this.post = post;
		this.account = account;
	}
	
	// Minus account (for insert)
	public CommentModel(String commentMessage, PostModel post) {
		super();
		this.commentMessage = commentMessage;
		this.post = post;
	}

	@Transient
	transient String accountImage;
	@Transient
	transient String username;

	@Override
	public String toString() {
		return "CommentModel [commentId=" + commentId + ", commentMessage=" + commentMessage + "account=" + account + "]";
	}

	
	
	
}
