package com.example.jsonmockserver.helper;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostBuilder {
    private final StoredFileDeserializer storedFileDeserializer;

    @Autowired
    public PostBuilder(StoredFileDeserializer storedFileDeserializer) {
        this.storedFileDeserializer = storedFileDeserializer;
    }

    public Posts buildPost(AddPostRequest addPostRequest) {
        Long possiblePostId = getPostNextId();
        return new Posts(possiblePostId, addPostRequest.getTitle(), addPostRequest.getAuthor(), addPostRequest.getViews(), addPostRequest.getReviews());
    }

    public void savePostToFile(Posts posts) {
        storedFileDeserializer.savePostToFile(posts);
    }

    public Posts getPost(Long postId) throws InvalidDataException {
        FileData fileData = getFileData();
        Set<Posts> posts = new HashSet<>(fileData.getPosts());
        Posts postResult;
        Optional<Posts> optionalPosts = posts.stream().filter(post -> post.getId() == postId).findFirst();
        if (optionalPosts.isPresent()) {
            postResult = optionalPosts.get();
        } else {
            throw new InvalidDataException("Post not found With Post Id : " + postId);
        }

        return postResult;
    }


    public void deletePostFromFile(Long postId) {
        FileData fileData = getFileData();
        Map<Long, Posts> postsMap = getPostMap(fileData);
        if (postsMap.containsKey(postId)) {
            postsMap.remove(postId);
            fileData.setPosts(new ArrayList<>(postsMap.values()));
            storedFileDeserializer.updateFileDataForPosts(fileData);
        }
    }

    public List<Posts> getAllPosts() {
        FileData fileData = getFileData();
        return fileData.getPosts();
    }

    private Long getPostNextId() {
        FileData fileData = getFileData();
        List<Posts> posts = fileData.getPosts();
        Posts maxPossibleIdPost = posts
                .stream()
                .max(Comparator.comparing(Posts::getId))
                .orElse(new Posts(0L));
        return maxPossibleIdPost.getId() + 1;
    }

    private FileData getFileData() {
        return storedFileDeserializer.getFileData();
    }

    private Map<Long, Posts> getPostMap(FileData fileData) {
        Set<Posts> posts = new HashSet<>(fileData.getPosts());
        Map<Long, Posts> postMap = new HashMap<>();
        for (Posts post : posts) {
            postMap.put(post.getId(), post);
        }
        return postMap;
    }
}
