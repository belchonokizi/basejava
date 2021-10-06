package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected static int size = 0;

    public int size() {
        return size;
    }

    //паттерн шаблонный метод - несложный алгоритм
    //который позволяет наследникам реализовывать по-совему его небольшую
    // часть, в данном случае - метод getIndex
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " is not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("Resume " + resume.getUuid() + " already exists");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public abstract void insertElement(Resume resume, int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " is not exists");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " is not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public abstract void fillDeletedElement(int index);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}
