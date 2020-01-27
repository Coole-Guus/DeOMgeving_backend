package org.example.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;

public class UploadedFile {

    private int experimentId;

    private String fileData;

    private String fileName;

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }
}
