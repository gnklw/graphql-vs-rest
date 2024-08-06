package com.demo.graphqlvsrest.rest;

import com.demo.graphqlvsrest.mapper.AwardMapper;
import com.demo.graphqlvsrest.model.dto.AwardDTO;
import com.demo.graphqlvsrest.model.entity.AwardEntity;
import com.demo.graphqlvsrest.repo.AwardRepository;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restAwardController")
@RequestMapping("/awards")
public class AwardController {

    private final AwardRepository awardRepository;
    private final AuthorRepository authorRepository;
    private final AwardMapper awardMapper;

    public AwardController(AwardRepository awardRepository, AuthorRepository authorRepository, AwardMapper awardMapper) {
        this.awardRepository = awardRepository;
        this.authorRepository = authorRepository;
        this.awardMapper = awardMapper;
    }

    @GetMapping
    public List<AwardDTO> getAllAwards() {
        return awardRepository.findAll().stream()
                .map(awardMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AwardDTO getAwardById(@PathVariable Long id) {
        return awardRepository.findById(id)
                .map(awardMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public AwardDTO createAward(@RequestBody AwardDTO awardDTO) {
        AwardEntity awardEntity = awardMapper.toEntity(awardDTO);
        awardEntity.setAuthor(authorRepository.findById(awardDTO.author().id()).orElse(null));
        return awardMapper.toDTO(awardRepository.save(awardEntity));
    }

    @PutMapping("/{id}")
    public AwardDTO updateAward(@PathVariable Long id, @RequestBody AwardDTO awardDetails) {
        AwardEntity awardEntity = awardRepository.findById(id).orElse(null);
        if (awardEntity != null) {
            awardEntity.setName(awardDetails.name());
            awardEntity.setYear(awardDetails.year());
            awardEntity.setAuthor(authorRepository.findById(awardDetails.author().id()).orElse(null));
            return awardMapper.toDTO(awardRepository.save(awardEntity));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAward(@PathVariable Long id) {
        awardRepository.deleteById(id);
    }
}