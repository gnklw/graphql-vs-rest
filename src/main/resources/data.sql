INSERT INTO authors (name, birthdate, nationality)
VALUES ('Author One', '1970-01-01', 'American'),
       ('Author Two', '1980-02-02', 'British'),
       ('Author Three', '1965-03-03', 'Canadian'),
       ('Author Four', '1990-04-04', 'Australian'),
       ('Author Five', '1975-05-05', 'German');

INSERT INTO books (title, publication_date, genre, author_id)
VALUES ('Book One', '2000-01-01', 'Fiction', 1),
       ('Book Two', '2010-02-02', 'Non-Fiction', 2),
       ('Book Three', '2005-03-03', 'Mystery', 3),
       ('Book Four', '2015-04-04', 'Biography', 4),
       ('Book Five', '2003-05-05', 'Science Fiction', 5),
       ('Book Six', '2008-06-06', 'Romance', 1),
       ('Book Seven', '2012-07-07', 'Fantasy', 2),
       ('Book Eight', '2018-08-08', 'Thriller', 3),
       ('Book Nine', '2011-09-09', 'Horror', 4),
       ('Book Ten', '2020-10-10', 'Historical Fiction', 5);

INSERT INTO publishers (name, address, contact)
VALUES ('Publisher One', '123 Main St', 'contact@publisherone.com'),
       ('Publisher Two', '456 Elm St', 'contact@publishertwo.com'),
       ('Publisher Three', '789 Oak St', 'contact@publisherthree.com'),
       ('Publisher Four', '101 Pine St', 'contact@publisherfour.com'),
       ('Publisher Five', '202 Birch St', 'contact@publisherfive.com');

INSERT INTO book_publisher (book_id, publisher_id, published_date)
VALUES (1, 1, '2000-03-01'),
       (2, 2, '2010-04-01'),
       (3, 3, '2005-05-01'),
       (4, 4, '2015-06-01'),
       (5, 5, '2003-07-01'),
       (6, 1, '2008-08-01'),
       (7, 2, '2012-09-01'),
       (8, 3, '2018-10-01'),
       (9, 4, '2011-11-01'),
       (10, 5, '2020-12-01');

INSERT INTO reviews (book_id, reviewer_name, rating, comments)
VALUES (1, 'Reviewer One', 5, 'Excellent book!'),
       (2, 'Reviewer Two', 4, 'Very good read.'),
       (3, 'Reviewer Three', 3, 'Interesting story.'),
       (4, 'Reviewer Four', 5, 'Inspirational biography.'),
       (5, 'Reviewer Five', 2, 'Not my type.'),
       (6, 'Reviewer Six', 4, 'Great romance.'),
       (7, 'Reviewer Seven', 5, 'Amazing fantasy world.'),
       (8, 'Reviewer Eight', 3, 'Thrilling but predictable.'),
       (9, 'Reviewer Nine', 4, 'Scary and engaging.'),
       (10, 'Reviewer Ten', 5, 'Well-written historical fiction.');

INSERT INTO awards (name, year, author_id)
VALUES ('Best Author', 2001, 1),
       ('Top Writer', 2011, 2),
       ('Mystery Award', 2006, 3),
       ('Biography Excellence', 2016, 4),
       ('Sci-Fi Master', 2004, 5),
       ('Romance Novel of the Year', 2009, 1),
       ('Fantasy Creator Award', 2013, 2),
       ('Thriller Prize', 2019, 3),
       ('Horror Story Award', 2012, 4),
       ('Historical Fiction Award', 2021, 5);