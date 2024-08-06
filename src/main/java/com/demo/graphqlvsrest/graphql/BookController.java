package com.demo.graphqlvsrest.graphql;

import com.demo.graphqlvsrest.mapper.BookMapper;
import com.demo.graphqlvsrest.model.dto.BookDTO;
import com.demo.graphqlvsrest.model.entity.BookEntity;
import com.demo.graphqlvsrest.repo.AuthorRepository;
import com.demo.graphqlvsrest.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller("graphqlBookController")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @QueryMapping
    public List<BookDTO> allBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @QueryMapping
    public BookDTO bookById(@Argument Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO)
                .orElse(null);
    }

    @MutationMapping
    public BookDTO createBook(@Argument String title, @Argument LocalDate publicationDate, @Argument String genre, @Argument Long authorId) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        bookEntity.setPublicationDate(publicationDate);
        bookEntity.setGenre(genre);
        bookEntity.setAuthor(authorRepository.findById(authorId).orElse(null));
        return bookMapper.toDTO(bookRepository.save(bookEntity));
    }

    @MutationMapping
    public BookDTO updateBook(@Argument Long id, @Argument String title, @Argument LocalDate publicationDate, @Argument String genre, @Argument Long authorId) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null) {
            bookEntity.setTitle(title);
            bookEntity.setPublicationDate(publicationDate);
            bookEntity.setGenre(genre);
            bookEntity.setAuthor(authorRepository.findById(authorId).orElse(null));
            return bookMapper.toDTO(bookRepository.save(bookEntity));
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        bookRepository.deleteById(id);
        return true;
    }
}