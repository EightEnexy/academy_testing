package sk.ness.academy.springboothibernate.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.dto.BookDto;
import sk.ness.academy.springboothibernate.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplTest {

  @Mock
  private BookDao bookDao;

  @InjectMocks
  private BookServiceImpl bookService;

  private List<Book> books;

  @Test
  void testFindAll() {

    Mockito.when(bookDao.findAll()).thenReturn(books);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(3, bookDtos.size());
    Assertions.assertEquals("X_Book_1", bookDtos.get(0).getName());
    Assertions.assertEquals("X_Book_2", bookDtos.get(1).getName());
    Assertions.assertEquals("X_Book_3", bookDtos.get(2).getName());
  }

  @Test
  void testFindAllEmpty() {
    Mockito.when(bookDao.findAll()).thenReturn(new ArrayList<>());

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertEquals(0, bookDtos.size());
  }


  @Test
  void testFindAllNull() {
    Mockito.when(bookDao.findAll()).thenReturn(null);

    final List<BookDto> bookDtos = bookService.findAll();

    Assertions.assertTrue(bookDtos.isEmpty());
  }

  @Test
  void testPersist() {

    Book testBook = new Book();
    testBook.setName("Book_1");
    testBook.setId(1L);

    BookServiceImpl service = mock(BookServiceImpl.class);
    service.save(testBook);
    service.save(testBook);
    verify(service, times(2)).save(testBook);

  }

  @BeforeEach
  private void init() {


    final Book book1 = new Book();
    book1.setName("Book_1");
    book1.setId(1L);

    final Book book2 = new Book();
    book2.setName("Book_2");
    book2.setId(2L);

    final Book book3 = new Book();
    book3.setName("Book_3");
    book3.setId(3L);

    books = new ArrayList<>();
    books.add(book1);
    books.add(book2);
    books.add(book3);
  }
}