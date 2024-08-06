package com.demo.graphqlvsrest.graphql;

import com.demo.graphqlvsrest.mapper.AuthorMapper;
import com.demo.graphqlvsrest.model.dto.AuthorDTO;
import com.demo.graphqlvsrest.model.entity.AuthorEntity;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller("graphqlAuthorController")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @QueryMapping
    public List<AuthorDTO> allAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @QueryMapping
    public AuthorDTO authorById(@Argument Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElse(null);
    }

    @MutationMapping
    public AuthorDTO createAuthor(@Argument String name, @Argument LocalDate birthdate, @Argument String nationality) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(name);
        authorEntity.setBirthdate(birthdate);
        authorEntity.setNationality(nationality);
        return authorMapper.toDTO(authorRepository.save(authorEntity));
    }

    @MutationMapping
    public AuthorDTO updateAuthor(@Argument Long id, @Argument String name, @Argument LocalDate birthdate, @Argument String nationality) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElse(null);
        if (authorEntity != null) {
            authorEntity.setName(name);
            authorEntity.setBirthdate(birthdate);
            authorEntity.setNationality(nationality);
            return authorMapper.toDTO(authorRepository.save(authorEntity));
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteAuthor(@Argument Long id) {
        authorRepository.deleteById(id);
        return true;
    }
}