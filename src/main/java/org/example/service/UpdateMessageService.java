package org.example.service;

import org.example.model.ExperimentDetails;
import org.example.model.Message;
import org.example.persistence.UpdateMessageDAO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class UpdateMessageService extends BaseService<ExperimentDetails> {

    private final UpdateMessageDAO dao;

    @Inject
    public UpdateMessageService(UpdateMessageDAO dao) {
        this.dao = dao;
    }

    public List<Message> getAllMessages(int experimentId) {
        return dao.getAllMessages(experimentId);
    }

    public Response addMessage(Message newMessage, int experimentId) {
        int result = dao.postNewMessage(newMessage, experimentId);
        int MESSAGE_CREATED = 1;


        if (result == MESSAGE_CREATED) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.CONFLICT).build();
    }
}
