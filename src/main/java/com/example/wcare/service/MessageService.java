package com.example.wcare.service;

import com.example.wcare.dao.MessageRepository;
import com.example.wcare.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    private MessageRepository repo;

    @Autowired
    public MessageService(MessageRepository repo){
        this.repo = repo;
    }

    public List<Message> listAll() {
        return repo.findAll();
    }

    public void save(Message message) {
        repo.save(message);
    }

    public Message get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
