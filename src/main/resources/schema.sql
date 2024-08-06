drop table if exists awards;
drop table if exists reviews;
drop table if exists book_publisher;
drop table if exists publishers;
drop table if exists books;
drop table if exists authors;

CREATE TABLE authors
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    birthdate   DATE,
    nationality VARCHAR(50)
);

CREATE TABLE books
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR(100) NOT NULL,
    publication_date DATE,
    genre            VARCHAR(50),
    author_id        BIGINT      NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
);

CREATE TABLE publishers
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    contact VARCHAR(100)
);

CREATE TABLE book_publisher
(
    book_id        BIGINT NOT NULL,
    publisher_id   BIGINT NOT NULL,
    published_date DATE,
    CONSTRAINT pk_book_publisher PRIMARY KEY (book_id, publisher_id),
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES books (id),
    CONSTRAINT fk_publisher
        FOREIGN KEY (publisher_id)
            REFERENCES publishers (id)
);

CREATE TABLE reviews
(
    id            BIGSERIAL PRIMARY KEY,
    book_id       BIGINT      NOT NULL,
    reviewer_name VARCHAR(100) NOT NULL,
    rating        INTEGER CHECK (rating >= 1 AND rating <= 5),
    comments      TEXT,
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
);

CREATE TABLE awards
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    year      INTEGER      NOT NULL,
    author_id BIGINT      NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author_id)
            REFERENCES authors (id)
);