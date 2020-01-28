package org.example.service;

import org.example.model.ExperimentDetails;
import org.example.model.Message;
import org.example.persistence.DAOFactory;
import org.example.persistence.UpdateMessageDAO;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

public class UpdateMessageService extends BaseService<ExperimentDetails> {

    private final UpdateMessageDAO dao;

    @Inject
    public UpdateMessageService(DAOFactory factory) {
        this.dao = factory.onDemand(UpdateMessageDAO.class);
    }

    public List<Message> getAllMessages(int experimentId) {
        List<Message> messageList = dao.getAllMessages(experimentId);
        dao.close();
        return messageList;
    }

    public Response addMessage(Message newMessage, int experimentId) {
        int result = dao.postNewMessage(newMessage, experimentId);
        dao.close();

        int MESSAGE_CREATED = 1;
        if (result == MESSAGE_CREATED) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.CONFLICT).build();
    }
}
