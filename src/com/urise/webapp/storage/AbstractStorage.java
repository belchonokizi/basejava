package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NoExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

//SK - тип данных, с которым работает каждая конкретная реализация
public abstract class AbstractStorage<SK> implements Storage {

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract List<Resume> doCopyAll();

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NoExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
