package com.ca.security.roles.demo78.services.Impl;

import com.ca.security.roles.demo78.filtering.DoneFilterOption;
import com.ca.security.roles.demo78.filtering.FilterAdjuster;
import com.ca.security.roles.demo78.persist.entities.Note;
import com.ca.security.roles.demo78.persist.entities.User;
import com.ca.security.roles.demo78.persist.repositories.NoteRepository;
import com.ca.security.roles.demo78.services.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private NoteRepository repository;

    @Autowired
    public void setRepository(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note getById(Integer id, User user) {
        Note note = repository.getReferenceById(id);

        if (user == null || note.getUser() == null || !user.getId().equals(note.getUser().getId())) {
            throw new IllegalArgumentException();
        }
        return note;
    }

    @Override
    public void save(Note note) {
        repository.save(note);
    }

    @Override
    public void update(Integer id, String message, boolean done, User user) {
        Note note = repository.getReferenceById(id);

        if (user == null || note.getUser() == null || !user.getId().equals(note.getUser().getId())) {
            throw new IllegalArgumentException();
        }

        note.setMessage(message);
        note.setDone(done);
        note.setUser(user);
        repository.save(note);
    }

    @Override
    public void delete(Integer id, User user) {
        Note note = repository.getReferenceById(id);

        if (user == null || note.getUser() == null || !user.getId().equals(note.getUser().getId())) {
            throw new IllegalArgumentException();
        }
        repository.deleteById(id);
    }

    @Override
    public Page<Note> findByUser(Pageable pageable, User user) {
        return repository.findByUser(pageable, user);
    }

    @Override
    public Page<Note> findByUserAndSearchParameters(
            Pageable pageable, User user, FilterAdjuster filterAdjuster) {

        if (filterAdjuster.isAll()) {
            return findByUser(pageable, user);
        }
        if (filterAdjuster.getDone() == DoneFilterOption.ALL && !filterAdjuster.getSearchText().isEmpty()) {
            return repository.findByUserAndMessageContaining(
                    pageable, user, filterAdjuster.getSearchText());
        }
        if (filterAdjuster.getDone() != DoneFilterOption.ALL && filterAdjuster.getSearchText().isEmpty()) {
            return repository.findByUserAndDone(pageable,
                    user, filterAdjuster.getDone() == DoneFilterOption.DONE);
        }
        return repository.findByUserAndDoneAndMessageContaining(pageable,
                user, filterAdjuster.getDone() == DoneFilterOption.DONE, filterAdjuster.getSearchText());
    }
}
