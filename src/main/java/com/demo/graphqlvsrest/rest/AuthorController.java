package com.demo.graphqlvsrest.rest;

import com.demo.graphqlvsrest.mapper.AuthorMapper;
import com.demo.graphqlvsrest.model.dto.AuthorDTO;
import com.demo.graphqlvsrest.model.entity.AuthorEntity;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restAuthorController")
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return authorRepository
                .findById(id)
                .map(authorMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO author) {
        return authorMapper.toDTO(authorRepository.save(authorMapper.toEntity(author)));
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDetails) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElse(null);
        if (authorEntity != null) {
            authorEntity.setName(authorDetails.name());
            authorEntity.setBirthdate(authorDetails.birthdate());
            authorEntity.setNationality(authorDetails.nationality());
            return authorMapper.toDTO(authorRepository.save(authorEntity));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}
