package com.thatvineyard.snapswriter.persistence.filehandler;


public class File {

    public enum FileType {
        RAW, JSON;
    }

    private String fileId;
    private FileType fileType;
    private String filepath;

    public File(String fileId, FileType fileType, String filepath) {
        this.fileId = fileId;
        this.fileType = fileType;
        this.filepath = filepath;
    }

    public String getFileId() {
        return fileId;
    }

    public FileType getFileType() {
        return fileType;
    }

    public String getFilepath() {
        return filepath;
    }
}
