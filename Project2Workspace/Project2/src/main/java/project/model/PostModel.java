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

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_posts")
public class PostModel {

	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_message", nullable=false, length = 140)
	private String postMessage;
	
	@Column(name="post_likes")
	private int numOfLikes;

	@Column(name="post_image")
	private String postImage;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CommentModel> comments = new ArrayList<CommentModel>();
	

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="account_id", nullable=false)
	@JsonIgnore
	private AccountModel account;

	// All but ID Args Constructor
	public PostModel(String postMessage, int numOfLikes, String postImage, AccountModel account) {
		super();
		this.postMessage = postMessage;
		this.numOfLikes = numOfLikes;
		this.postImage = postImage;
		this.account = account;

	}

	@Override
	public String toString() {
		return "PostModel [postId=" + postId + ", postMessage=" + postMessage + ", numOfLikes=" + numOfLikes
				+ ", postImage=" + postImage+ "]";
	}
	 
	
}
