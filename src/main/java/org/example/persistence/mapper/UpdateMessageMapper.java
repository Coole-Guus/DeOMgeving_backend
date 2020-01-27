package org.example.persistence.mapper;

import org.example.model.Message;
import org.example.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateMessageMapper implements ResultSetMapper<Message> {

    @Override
    public Message map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Message message = new Message();
        message.setContent(resultSet.getString("message_tekst"));
        message.setAuthor(resultSet.getString("message_auteur"));
        message.setPostedDate(resultSet.getString("message_datum"));

        return message;
    }
}
