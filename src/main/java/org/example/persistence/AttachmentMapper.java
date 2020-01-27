package org.example.persistence;

import org.example.model.UploadedFile;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentMapper implements ResultSetMapper<UploadedFile> {
    @Override
    public UploadedFile map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        UploadedFile file = new UploadedFile();

        file.setFileData(resultSet.getString("attachment"));
        file.setFileName(resultSet.getString("attachmentName"));
        file.setExperimentId(resultSet.getInt("experiment_ID"));

        return file;
    }
}
