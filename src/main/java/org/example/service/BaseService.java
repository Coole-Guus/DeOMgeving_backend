package org.example.service;

import javax.ws.rs.NotFoundException;

public class BaseService<T> {
    public T requireResult(T model)
    {
        if (model == null)
        {
            throw new NotFoundException();
        }

        return model;
    }
}
