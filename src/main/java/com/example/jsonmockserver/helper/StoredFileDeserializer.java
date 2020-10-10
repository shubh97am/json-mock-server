package com.example.jsonmockserver.helper;

import com.example.jsonmockserver.common.constants.Constant;
import com.example.jsonmockserver.common.exception.StoredFileReadWriteException;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.InputStream;
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

    public FileData getFileData() {
        try (InputStream in = StoredFileDeserializer.class.getResourceAsStream(READ_FILE_PATH)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(in, new TypeReference<FileData>() {
            });

        } catch (Exception e) {
            throw new StoredFileReadWriteException("error while deserialize StoredFile");
        }
    }

    private void updateFileData(FileData fileData) {
        try {
            Gson gson = new Gson();
            gson.toJson(fileData, new FileWriter(WRITE_FILE_PATH));
        } catch (Exception e) {
            throw new StoredFileReadWriteException("error while updating StoredFile");
        }
    }


}
