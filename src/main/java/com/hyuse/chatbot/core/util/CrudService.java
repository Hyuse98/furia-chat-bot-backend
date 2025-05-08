package com.hyuse.chatbot.core.util;

import java.util.List;

public interface CrudService<T, ID> {
    T create(T entity);
    T getById(ID id);
    List<T> getAll();
    T update(T entity, ID id);
    void deleteById(ID id);
}
