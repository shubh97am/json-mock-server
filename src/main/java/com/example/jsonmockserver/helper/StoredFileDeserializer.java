package com.example.jsonmockserver.helper;

import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.StoredFileReadWriteException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StoredFileDeserializer {

    private static final String READ_FILE_PATH = Constant.Fields.READ_STORED_RESULT_FILE_PATH;
    private static final String WRITE_FILE_PATH = Constant.Fields.WRITE_STORED_RESULT_FILE_PATH;


    public void savePostToFile(Posts post) {
        FileData fileData = getFileData();
        List<Posts> posts = fileData.getPosts();
        posts.add(post);
        fileData.setPosts(posts);
        updateFileData(fileData);
    }

    public void saveAuthorToFile(Authors author) {
        FileData fileData = getFileData();
        List<Authors> authors = fileData.getAuthors();
        authors.add(author);
        fileData.setAuthors(authors);
        updateFileData(fileData);
    }

    public void updateFileDataForPosts(FileData fileData) {
        updateFileData(fileData);
    }

    public void updateFileDataForAuthors(FileData fileData) {
        updateFileData(fileData);
    }

    public FileData getFileData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileData fileData = objectMapper.readValue(new File(READ_FILE_PATH), FileData.class);
            return fileData;
        } catch (Exception e) {
            throw new StoredFileReadWriteException("error while deserialize StoredFile");
        }
    }

    public void clearFileData() {
        FileData fileData = new FileData();
        fileData.setAuthors(new ArrayList<>());
        fileData.setPosts(new ArrayList<>());
        updateFileData(fileData);
    }


    private void updateFileData(FileData fileData) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            objectMapper.writeValue(new File(WRITE_FILE_PATH), fileData);
        } catch (Exception e) {
            throw new StoredFileReadWriteException("error While Updating File");
        }
//        File file = new File(WRITE_FILE_PATH);
//        boolean exists = file.exists();
//        try {
//            if (exists == true) {
//                Gson gson = new Gson();
//                gson.toJson(fileData, new FileWriter(file));
//
//            }
//        } catch (Exception e) {
//            throw new StoredFileReadWriteException("error while updating StoredFile");
//        }

    }


}
