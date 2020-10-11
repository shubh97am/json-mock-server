package com.example.jsonmockserver.helper;

import com.example.jsonmockserver.common.exception.InvalidDataException;
import com.example.jsonmockserver.dto.pojo.Authors;
import com.example.jsonmockserver.dto.pojo.FileData;
import com.example.jsonmockserver.dto.pojo.Posts;
import com.example.jsonmockserver.dto.requests.AddAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthorBuilder {

    private final StoredFileDeserializer storedFileDeserializer;

    @Autowired
    public AuthorBuilder(StoredFileDeserializer storedFileDeserializer) {
        this.storedFileDeserializer = storedFileDeserializer;
    }

    public Authors buildAuthor(AddAuthorRequest addAuthorRequest) {
        Long possibleAuthorId = getAuthorNextId();
        return new Authors(possibleAuthorId, addAuthorRequest.getFirstName(), addAuthorRequest.getLastName(), addAuthorRequest.getPosts());
    }

    public void saveAuthorToFile(Authors authors) {
        storedFileDeserializer.saveAuthorToFile(authors);
    }


    public Authors getAuthor(Long authorId) throws InvalidDataException {
        FileData fileData = getFileData();
        Set<Authors> authors = new HashSet<>(fileData.getAuthors());
        Authors authorsResult;
        Optional<Authors> optionalAuthors = authors.stream().filter(author -> author.getId() == authorId).findFirst();
        if (optionalAuthors.isPresent()) {
            authorsResult = optionalAuthors.get();
        } else {
            throw new InvalidDataException("Author not found With Author Id : " + authorId);
        }

        return authorsResult;
    }

    public void deleteAuthorFromFile(Long authorId) {
        FileData fileData = getFileData();
        Map<Long, Authors> authorsMap = getAuthorsMap(fileData);
        if (authorsMap.containsKey(authorId)) {
            authorsMap.remove(authorId);
            fileData.setAuthors(new ArrayList<>(authorsMap.values()));
            storedFileDeserializer.updateFileDataForAuthors(fileData);
        }
    }

    public List<Authors> getAllAuthors() {
        FileData fileData = getFileData();
        List<Authors> authors = fileData.getAuthors();
        return authors;
    }

    private Long getAuthorNextId() {
        FileData fileData = getFileData();
        List<Authors> authors = fileData.getAuthors();
        Authors maxPossibleIdAuthor = authors
                .stream()
                .max(Comparator.comparing(Authors::getId))
                .orElse(new Authors(0L));
        return maxPossibleIdAuthor.getId() + 1;
    }

    private FileData getFileData() {
        return storedFileDeserializer.getFileData();
    }

    private Map<Long, Authors> getAuthorsMap(FileData fileData) {
        Set<Authors> authors = new HashSet<>(fileData.getAuthors());
        Map<Long, Authors> authorsMap = new HashMap<>();
        for (Authors author : authors) {
            authorsMap.put(author.getId(), author);
        }
        return authorsMap;
    }
}
