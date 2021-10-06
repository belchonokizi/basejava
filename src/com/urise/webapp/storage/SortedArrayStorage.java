package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void insertElement(Resume resume, int index) {
        //https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIDX = -index - 1;
        System.arraycopy(storage, insertIDX, storage, insertIDX + 1, size - insertIDX);
        storage[insertIDX] = resume;
    }

    @Override
    public void fillDeletedElement(int index) {
         int numMoved = size - index - 1;
         if (numMoved > 0) {
             System.arraycopy(storage, index + 1, storage, index, numMoved);
         }
    }

}
