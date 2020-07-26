package com.dawn.ebms.serviceimpl;

import com.dawn.ebms.dao.BookDao;
import com.dawn.ebms.entity.Book;
import com.dawn.ebms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    public Book addBookByIsbn(String isbn){
        if(bookDao.findBookByIsbn(isbn).isPresent())
            return null;
        else
            return bookDao.addBookByIsbn(isbn);
    }

    public Book addBookByName(String name){
        if(bookDao.findBookByName(name).isPresent())
            return null;
        else
            return bookDao.addBookByName(name);
    }

    public Book findBookByBookId(int id) {
        return bookDao.findBookByBookId(id);
    }

    public List<Book> findBookBySearch(String search) { return bookDao.findBookBySearch(search);}

    public List<Integer> findBookByNames(String books)
    {
        return bookDao.findBookByNames(books);
    }


    public void deleteBookByBookId(Integer id){
        bookDao.deleteBookByBookId(id);
    }

    public Integer updateBook(Integer type, Integer id, String value)
    {
        return bookDao.updateBook(type, id, value);
    }
}
