package com.urise.webapp.exception;

public class NoExistStorageException extends StorageException{
    public NoExistStorageException(String uuid) {
        super("Resume " + uuid + " does not exist", uuid);
    }
}
