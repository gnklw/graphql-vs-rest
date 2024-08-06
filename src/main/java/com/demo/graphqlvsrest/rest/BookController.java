package com.demo.graphqlvsrest.rest;

import com.demo.graphqlvsrest.mapper.BookMapper;
import com.demo.graphqlvsrest.model.dto.BookDTO;
import com.demo.graphqlvsrest.model.entity.BookEntity;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import com.demo.graphqlvsrest.repo.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restBookController")
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        BookEntity bookEntity = bookMapper.toEntity(bookDTO);
        bookEntity.setAuthor(authorRepository.findById(bookDTO.author().id()).orElse(null));
        return bookMapper.toDTO(bookRepository.save(bookEntity));
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDetails) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null) {
            bookEntity.setTitle(bookDetails.title());
            bookEntity.setPublicationDate(bookDetails.publicationDate());
            bookEntity.setGenre(bookDetails.genre());
            bookEntity.setAuthor(authorRepository.findById(bookDetails.author().id()).orElse(null));
            return bookMapper.toDTO(bookRepository.save(bookEntity));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}