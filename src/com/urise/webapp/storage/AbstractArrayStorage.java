package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
