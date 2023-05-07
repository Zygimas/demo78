package com.ca.security.roles.demo78.services;

import com.ca.security.roles.demo78.filtering.FilterAdjuster;
import com.ca.security.roles.demo78.persist.entities.Note;
import com.ca.security.roles.demo78.persist.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {

    Note getById(Integer id, User user);

    void save(Note note);

    void update(Integer id, String message, boolean done, User user);

    void delete(Integer id, User user);

    Page<Note> findByUser(Pageable pageable, User user);

    Page<Note> findByUserAndSearchParameters(Pageable pageable, User user, FilterAdjuster filterAdjuster);
}
