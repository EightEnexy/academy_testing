package sk.ness.academy.springboothibernate.dao.impl;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.ness.academy.config.TestDataSourceConfig;
import sk.ness.academy.springboothibernate.dao.BookDao;
import sk.ness.academy.springboothibernate.dto.BookDto;
import sk.ness.academy.springboothibernate.model.Book;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = { TestDataSourceConfig.class, BookDaoImpl.class })
@Transactional
@Sql({"/initdb.sql"})
class BookDaoImplTest {

  @Autowired
  private BookDao bookDao;

  @Autowired
  private SessionFactory sessionFactory;


  @Test
  void findAllTest() {

    final List<Book> books = bookDao.findAll();

    Assertions.assertEquals(3, books.size());
    Assertions.assertEquals("Book 1", books.get(0).getName());
    Assertions.assertEquals("Book 2", books.get(1).getName());
    Assertions.assertEquals("Book 3", books.get(2).getName());

  }

  @BeforeEach
  public void beforeEach() {
    System.out.println("### BeforeEach ###");
  }

  @Test
  @Rollback(value = true)
  public void testPersist()
  {
    Book book = new Book();
    book.setName("TEST1");

    Book book1 = new Book();
    book1.setName("TEST2");

    Book book2 = new Book();
    book2.setName("TEST3");

    bookDao.persist(book);
    bookDao.persist(book1);
    bookDao.persist(book2);


    //final List<Book> books = bookDao.findAll();

    //Assertions.assertEquals(6, books.size());
  }


}

