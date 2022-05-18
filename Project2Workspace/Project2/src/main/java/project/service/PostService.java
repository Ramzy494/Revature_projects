package project.service;
import project.dao.AccountDao;
import project.dao.PostDao;
import project.model.AccountModel;
import project.model.PostModel;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	PostDao p;
	AccountDao DAO;
	ProfanityFilter F;

	@Autowired

	public PostService(PostDao p, AccountDao DAO,ProfanityFilter F) {
		super();
		this.p = p;
		this.DAO = DAO;
		this.F=F;
	}

	public PostModel postPost(PostModel post, HttpSession Session) {
		AccountModel User= (AccountModel)Session.getAttribute("currentUser");
		post.setAccount(User);
		// System.out.println(post.getAccount());



		post.setPostMessage(F.getCensoredText(post.getPostMessage()));
		return p.save(post);
	}

	public List<PostModel> GetPostByUser(int Id) {
		return fetchPostUserInfo(p.findAllByAccountAccountIdOrderByPostIdDesc(Id));
	}

	public PostModel LikePost(int Id) {
		PostModel postModel = p.findByPostId(Id);
		int likes = postModel.getNumOfLikes();
		likes++;
		postModel.setNumOfLikes(likes);
		return p.save(postModel);
	}

	/**
	 * Given an ID and image URL string, set that in the DB.
	 * 
	 * @param id
	 * @param imgUrl
	 * @author darkm (Frederick)
	 */
	public void updatePostImage(int id, String imgUrl) {
		PostModel post = p.findByPostId(id);
		post.setPostImage(imgUrl);
		p.save(post);
	}

	public List<PostModel> Readall() {
		return fetchPostUserInfo(p.findAll());

	}

	/**
	 * 
	 * @param page
	 * @return the sublist of the pages from a range.
	 */
	public List<PostModel> getPostsByPage(int page) {
		page--;
		int perPage = 5;
		int postIdEnd = ((int) p.count()) - page * perPage;
		int postIdStart = postIdEnd - page * perPage - perPage;
		return fetchPostUserInfo(p.findByPostIdBetweenOrderByPostIdDesc(postIdStart, postIdEnd + 1));
	}
	
	public List<PostModel> getPostsByUser(int id) {
		return fetchPostUserInfo(p.findAllByAccountAccountIdOrderByPostIdDesc(id));
	}
	
	public long getPostCount() {
		return p.count();
	}
	
	public List<PostModel> fetchPostUserInfo(List<PostModel> posts) {
		AccountModel acc;
		for(PostModel p : posts) {
			acc = p.getAccount();
			p.setUsername(acc.getUsername());
			p.setAccountImage(acc.getAccountImage());
		}
		return posts;
	}
	
	public PostModel fetchPostUserInfo(PostModel p) {
		AccountModel acc = p.getAccount();
		p.setUsername(acc.getUsername());
		p.setAccountImage(acc.getAccountImage());
		return p;
	}

}
