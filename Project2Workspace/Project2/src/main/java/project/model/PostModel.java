package project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_posts")
public class PostModel {

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@Column(name = "post_message", nullable = false, length = 140)
	private String postMessage;

	@Column(name = "post_likes")
	private int numOfLikes;

	@Column(name = "post_image")
	private String postImage;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonManagedReference(value="post-comment")
	private List<CommentModel> comments = new ArrayList<CommentModel>();

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", nullable = false)
	@JsonBackReference(value="account-post")
	private AccountModel account;

	// All but transient constructor
	public PostModel(int postId, String postMessage, int numOfLikes, String postImage, List<CommentModel> comments,
			AccountModel account) {
		super();
		this.postId = postId;
		this.postMessage = postMessage;
		this.numOfLikes = numOfLikes;
		this.postImage = postImage;
		this.comments = comments;
		this.account = account;
	}

	// All but ID Args Constructor
	public PostModel(String postMessage, int numOfLikes, String postImage, List<CommentModel> comments,
			AccountModel account) {
		super();
		this.postMessage = postMessage;
		this.numOfLikes = numOfLikes;
		this.postImage = postImage;
		this.comments = comments;
		this.account = account;
	}

	// For insert constructor
//	public PostModel(String postMessage, int numOfLikes, String postImage) {
//		super();
//		this.postMessage = postMessage;
//		this.numOfLikes = numOfLikes;
//	}

	@Transient
	transient String accountImage;
	@Transient
	transient String username;

	@Override
	public String toString() {
		return "PostModel [postId=" + postId + ", postMessage=" + postMessage + ", numOfLikes=" + numOfLikes
				+ ", postImage=" + postImage + ", comments=" + comments + "]";
	}

}
