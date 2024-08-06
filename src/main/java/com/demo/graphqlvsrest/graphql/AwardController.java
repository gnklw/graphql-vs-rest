package com.demo.graphqlvsrest.graphql;

import com.demo.graphqlvsrest.mapper.AwardMapper;
import com.demo.graphqlvsrest.model.dto.AwardDTO;
import com.demo.graphqlvsrest.model.entity.AwardEntity;
import com.demo.graphqlvsrest.repo.AwardRepository;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

@Controller("graphqlAwardController")
public class AwardController {

    private final AwardRepository awardRepository;
    private final AuthorRepository authorRepository;
    private final AwardMapper awardMapper;

    @Autowired
    public AwardController(AwardRepository awardRepository, AuthorRepository authorRepository, AwardMapper awardMapper) {
        this.awardRepository = awardRepository;
        this.authorRepository = authorRepository;
        this.awardMapper = awardMapper;
    }

    @QueryMapping
    public List<AwardDTO> allAwards() {
        return awardRepository.findAll().stream()
                .map(awardMapper::toDTO)
                .toList();
    }

    @QueryMapping
    public AwardDTO awardById(@Argument Long id) {
        return awardRepository.findById(id)
                .map(awardMapper::toDTO)
                .orElse(null);
    }

    @MutationMapping
    public AwardDTO createAward(@Argument String name, @Argument int year, @Argument Long authorId) {
        AwardEntity awardEntity = new AwardEntity();
        awardEntity.setName(name);
        awardEntity.setYear(year);
        awardEntity.setAuthor(authorRepository.findById(authorId).orElse(null));
        return awardMapper.toDTO(awardRepository.save(awardEntity));
    }

    @MutationMapping
    public AwardDTO updateAward(@Argument Long id, @Argument String name, @Argument int year, @Argument Long authorId) {
        AwardEntity awardEntity = awardRepository.findById(id).orElse(null);
        if (awardEntity != null) {
            awardEntity.setName(name);
            awardEntity.setYear(year);
            awardEntity.setAuthor(authorRepository.findById(authorId).orElse(null));
            return awardMapper.toDTO(awardRepository.save(awardEntity));
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteAward(@Argument Long id) {
        awardRepository.deleteById(id);
        return true;
    }
}