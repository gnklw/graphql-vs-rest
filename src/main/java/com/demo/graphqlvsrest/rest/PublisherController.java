package com.demo.graphqlvsrest.rest;

import com.demo.graphqlvsrest.mapper.PublisherMapper;
import com.demo.graphqlvsrest.model.dto.PublisherDTO;
import com.demo.graphqlvsrest.model.entity.PublisherEntity;
import com.demo.graphqlvsrest.repo.PublisherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restPublisherController")
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherController(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @GetMapping
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PublisherDTO getPublisherById(@PathVariable Long id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public PublisherDTO createPublisher(@RequestBody PublisherDTO publisherDTO) {
        PublisherEntity publisherEntity = publisherMapper.toEntity(publisherDTO);
        return publisherMapper.toDTO(publisherRepository.save(publisherEntity));
    }

    @PutMapping("/{id}")
    public PublisherDTO updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO publisherDetails) {
        PublisherEntity publisherEntity = publisherRepository.findById(id).orElse(null);
        if (publisherEntity != null) {
            publisherEntity.setName(publisherDetails.name());
            publisherEntity.setAddress(publisherDetails.address());
            publisherEntity.setContact(publisherDetails.contact());
            return publisherMapper.toDTO(publisherRepository.save(publisherEntity));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherRepository.deleteById(id);
    }
}