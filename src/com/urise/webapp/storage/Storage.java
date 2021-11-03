package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

//TODO refactoring
public interface Storage {
    /**
     * Array based storage for Resumes
     */
    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    //return list, sorted by name
    List<Resume> getAllSorted();

    int size();
}

