package com.example.demo.service.publisher.impl;

import com.example.demo.model.Genre;
import com.example.demo.model.Publisher;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.publisher.PublisherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    @Override
    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher consult(String uuid) {
        Optional<Publisher> publisherSearch = publisherRepository.findById(uuid);
        return publisherSearch.orElse(null);
    }

    @Override
    public List<Publisher> consultAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher modify(String uuid, Publisher publisher) {
        Publisher publisherSearch = consult(uuid);
        if (publisherSearch != null) {
            publisher.setUuid(uuid);
            return publisherRepository.save(publisher);
        }
        return null;
    }

    @Override
    public Publisher update(String uuid, Publisher publisher) {
        Publisher publisherSearch = consult(uuid);
        if (publisherSearch != null) {
            publisher.setUuid(uuid);
            Publisher publisherResponse = mapperUpdate(uuid, publisher, publisherSearch);
            return publisherRepository.save(publisherResponse);
        }
        return null;
    }

    @Override
    public void deleteById(String uuid) {
        Publisher publisher = consult(uuid);
        if (publisher != null) {
            publisherRepository.deleteById(uuid);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Publisher mapperUpdate(String uuid, Publisher publisherRequest, Publisher publisher) {
        Publisher publisherResponse = new Publisher();
        publisherResponse.setUuid(uuid);
        publisherResponse.setName(publisherRequest.getName() == null ? publisher.getName() : publisherRequest.getName());
        publisherResponse.setCreatedDate(publisherRequest.getCreatedDate() == null ? publisher.getCreatedDate() : publisherRequest.getCreatedDate());
        publisherResponse.setLastModifiedDate(publisherRequest.getLastModifiedDate() == null ? publisher.getLastModifiedDate() : publisherRequest.getLastModifiedDate());
        publisherResponse.setNit(publisherRequest.getNit() == null ? publisher.getNit() : publisherRequest.getNit());
        publisherResponse.setEmail(publisherRequest.getEmail() == null ? publisher.getEmail() : publisherRequest.getEmail());
        return publisherResponse;
    }
}
