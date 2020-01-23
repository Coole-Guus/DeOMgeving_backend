package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Message {

    @NotNull
    @NotEmpty
    private String author;

    @NotNull
    @NotEmpty
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-YYYY HH:mm")
    private Date postedDate;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
}
