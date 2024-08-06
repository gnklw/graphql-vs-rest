package com.demo.graphqlvsrest.graphql;

import com.demo.graphqlvsrest.mapper.PublisherMapper;
import com.demo.graphqlvsrest.model.dto.PublisherDTO;
import com.demo.graphqlvsrest.model.entity.PublisherEntity;
import com.demo.graphqlvsrest.repo.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("graphqlPublisherController")
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @QueryMapping
    public List<PublisherDTO> allPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toDTO)
                .toList();
    }

    @QueryMapping
    public PublisherDTO publisherById(@Argument Long id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElse(null);
    }

    @MutationMapping
    public PublisherDTO createPublisher(@Argument String name, @Argument String address, @Argument String contact) {
        PublisherEntity publisherEntity = new PublisherEntity();
        publisherEntity.setName(name);
        publisherEntity.setAddress(address);
        publisherEntity.setContact(contact);
        return publisherMapper.toDTO(publisherRepository.save(publisherEntity));
    }

    @MutationMapping
    public PublisherDTO updatePublisher(@Argument Long id, @Argument String name, @Argument String address, @Argument String contact) {
        PublisherEntity publisherEntity = publisherRepository.findById(id).orElse(null);
        if (publisherEntity != null) {
            publisherEntity.setName(name);
            publisherEntity.setAddress(address);
            publisherEntity.setContact(contact);
            return publisherMapper.toDTO(publisherRepository.save(publisherEntity));
        }
        return null;
    }

    @MutationMapping
    public Boolean deletePublisher(@Argument Long id) {
        publisherRepository.deleteById(id);
        return true;
    }
}