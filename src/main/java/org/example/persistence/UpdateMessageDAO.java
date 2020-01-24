package org.example.persistence;
import org.example.model.Message;
import org.example.persistence.mapper.UpdateMessageMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.ws.rs.PathParam;
import java.util.List;

public interface UpdateMessageDAO {

    @SqlQuery("SELECT * FROM message WHERE experiment_id = :id ORDER BY message_datum")
    @RegisterMapper(UpdateMessageMapper.class)
    public List<Message> getAllMessages(@Bind("id")int experimentId);

    @SqlUpdate("INSERT INTO message (experiment_ID, message_auteur, message_tekst, message_datum) " +
            "VALUES (:experimentId, :author, :content, CURRENT_TIMESTAMP)")
    public int postNewMessage(@BindBean Message newMessage, @Bind("experimentId") int experimentId);
}
