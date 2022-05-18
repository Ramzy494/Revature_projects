package project.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import project.AmazonS3Config;
import project.service.AccountService;
import project.service.PostService;

@RestController
public class S3Controller {
	PostService servicePost;
	AccountService serviceAccount;
	
	@Autowired
	private AmazonS3 myS3Client;

	@Autowired
	public S3Controller(PostService servicePost, AccountService serviceAccount, AmazonS3 myS3Client) {
		super();
		this.servicePost = servicePost;
		this.serviceAccount = serviceAccount;
		this.myS3Client = myS3Client;
	}

	@CrossOrigin
	@PostMapping("/uploadImageUser")
	public ResponseEntity<?> uploadImageUser(@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value = "id") Integer accountId) throws IOException {
		String tempFileName = UUID.randomUUID() + image.getOriginalFilename();
		File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
		image.transferTo(tempFile); // Convert multipart file to File
		String key = UUID.randomUUID() + image.getOriginalFilename(); // unique key for the file
		serviceAccount.updateImage(accountId, key);
		myS3Client.putObject(new PutObjectRequest(AmazonS3Config.getBucketName(), key, tempFile)); // Upload file
		tempFile.deleteOnExit(); // delete temp file
		return ResponseEntity.created(URI.create(key)).build();
	}

	@CrossOrigin
	@PostMapping("/uploadImagePost")
	public ResponseEntity<?> uploadImagePost(@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value = "id") Integer postId) throws IOException {
		String tempFileName = UUID.randomUUID() + image.getOriginalFilename();
		File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
		image.transferTo(tempFile); // Convert multipart file to File
		String key = UUID.randomUUID() + image.getOriginalFilename(); // unique key for the file
		servicePost.updatePostImage(postId, key);
		myS3Client.putObject(new PutObjectRequest(AmazonS3Config.getBucketName(), key, tempFile)); // Upload file
		tempFile.deleteOnExit(); // delete temp file
		return ResponseEntity.created(URI.create(key)).build();
	}

	@CrossOrigin
	@GetMapping("/s3img/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
		S3Object data = myS3Client.getObject(AmazonS3Config.getBucketName(), fileName); // fileName is key which is used while
																			// uploading the object
		S3ObjectInputStream objectContent = data.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectContent);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		objectContent.close();
		return ResponseEntity.ok().contentLength(bytes.length).header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(resource);
	}
}
