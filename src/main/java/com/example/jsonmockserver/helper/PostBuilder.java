package com.example.jsonmockserver.helper;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.BuildPostFilterSortRequest;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddPostRequest;
import com.example.jsonmockserver.dto.requests.UpdatePostRequest;
import com.example.jsonmockserver.enumeration.SortField;
import com.example.jsonmockserver.enumeration.SortType;
import com.example.jsonmockserver.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostBuilder {
    private final StoredFileDeserializer storedFileDeserializer;
    private final PostValidator postValidator;

    @Autowired
    public PostBuilder(StoredFileDeserializer storedFileDeserializer, PostValidator postValidator) {
        this.storedFileDeserializer = storedFileDeserializer;
        this.postValidator = postValidator;
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

    public List<Posts> getFilteredAndSortedPost(BuildPostFilterSortRequest request) throws InvalidDataException {
        FileData fileData = getFileData();
        List<Posts> posts = fileData.getPosts();
        List<Posts> result = posts;

        /**
         * <>Filtering</>
         */
        if (!StringUtils.isEmpty(request.getTitle()) && !StringUtils.isEmpty(request.getAuthor())) {

            result = posts
                    .stream()
                    .filter(post -> post.getTitle().equalsIgnoreCase(request.getTitle()))
                    .filter(post -> post.getAuthor().equalsIgnoreCase(request.getAuthor()))
                    .collect(Collectors.toList());


        } else if (!StringUtils.isEmpty(request.getTitle())) {
            result = posts
                    .stream()
                    .filter(post -> post.getTitle().equalsIgnoreCase(request.getTitle()))
                    .collect(Collectors.toList());
        } else if (!StringUtils.isEmpty(request.getAuthor())) {
            result = posts
                    .stream()
                    .filter(post -> post.getAuthor().equalsIgnoreCase(request.getAuthor()))
                    .collect(Collectors.toList());
        }

        /**
         * <>Sorting By default on Id</>
         */
        SortType sortType = SortType.getSortTypeByKey(request.getSortType());
        SortField sortField = SortField.getSortFieldByKey(request.getSortField());
        if (sortType.getKey().equalsIgnoreCase("asc")) {
            if (sortField.getKey().equalsIgnoreCase("id")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post1, Posts post2) {
                                return post1.getId().compareTo(post2.getId());
                            }
                        }
                );
            } else if (sortField.getKey().equalsIgnoreCase("views")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post1, Posts post2) {
                                return post1.getViews().compareTo(post2.getViews());
                            }
                        }
                );
            } else if (sortField.getKey().equalsIgnoreCase("reviews")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post1, Posts post2) {
                                return post1.getReviews().compareTo(post2.getReviews());
                            }
                        }
                );
            }
        } else if (sortType.getKey().equalsIgnoreCase("desc")) {

            if (sortField.getKey().equalsIgnoreCase("id")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post2, Posts post1) {
                                return post1.getId().compareTo(post2.getId());
                            }
                        }
                );
            } else if (sortField.getKey().equalsIgnoreCase("views")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post2, Posts post1) {
                                return post1.getViews().compareTo(post2.getViews());
                            }
                        }
                );
            } else if (sortField.getKey().equalsIgnoreCase("reviews")) {
                Collections.sort(
                        result,
                        new Comparator<Posts>() {
                            public int compare(Posts post2, Posts post1) {
                                return post1.getReviews().compareTo(post2.getReviews());
                            }
                        }
                );
            }
        }

        return result;
    }

    public Posts updatePost(Long postId, UpdatePostRequest updatePostRequest) throws InvalidDataException {
        Posts posts = new Posts();
        FileData fileData = getFileData();
        Map<Long, Posts> postsMap = getPostMap(fileData);
        if (!postsMap.containsKey(postId)) {
            throw new InvalidDataException("Post Update Fail Since Post Not Found With PostId : " + postId);
        }
        posts = postsMap.get(postId);
        postValidator.validateUpdatePostRequest(posts, updatePostRequest);
        postsMap.put(posts.getId(), posts);
        fileData.setPosts(new ArrayList<>(postsMap.values()));
        storedFileDeserializer.updateFileDataForPosts(fileData);
        return posts;
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
