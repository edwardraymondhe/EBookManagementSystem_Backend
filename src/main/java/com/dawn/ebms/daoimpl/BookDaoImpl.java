package com.dawn.ebms.daoimpl;

import com.dawn.ebms.dao.BookDao;
import com.dawn.ebms.entity.Book;
import com.dawn.ebms.entity.BookColl;
import com.dawn.ebms.repository.BookCollRepository;
import com.dawn.ebms.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCollRepository bookCollRepository;

    public List<Book> getBooks() {
        List<Book> bookList =  bookRepository.findAll();
        for(int i = 0 ; i < bookList.size(); i++)
        {
            Book book = bookList.get(i);
            Optional<BookColl> bookColl = bookCollRepository.findBookCollByBookIdEquals(book.getBookId());
            bookColl.ifPresent(book::setBookColl);
            bookList.set(i, book);
        }
        return bookList;
    }

    public List<Book> findBookBySearch(String search)
    {
        return bookRepository.findBookByNameContains(search);
    }

    public Book findBookByBookId(int id) {

        Book book = bookRepository.findBookByBookIdEquals(id).get();
        Optional<BookColl> bookColl = bookCollRepository.findBookCollByBookIdEquals(book.getBookId());
        bookColl.ifPresent(book::setBookColl);
        return book;
    }

    public List<Integer> findBookByNames(String books)
    {
        List<Integer> fetchBooks = new ArrayList<>();
        List<Book> fetchList = bookRepository.findBookByNameContains(books);
        for (Book book : fetchList) fetchBooks.add(book.getBookId());
        System.out.println("In findBookByNames");
        System.out.println(fetchBooks);
        return fetchBooks;
    }

    public void deleteBookByBookId(Integer id)
    {
        System.out.println("Deleting book");
        bookRepository.deleteById(id);
        bookCollRepository.deleteBookCollByBookIdEquals(id);
        System.out.println(bookCollRepository.findBookCollByBookIdEquals(id));
    }

    public Integer updateBook(Integer type, Integer id, String value)
    {

        switch(type){
            case 1:
                return setBookIsbnByBookId(id, value);
            case 2:
                return setBookNameByBookId(id, value);
            case 3:
                return setBookCategoryByBookId(id, value);
            case 4:
                return setBookAuthorByBookId(id, value);
            case 5:
                return setBookPriceByBookId(id, Double.valueOf(value));
            case 6:
                return setBookLanguageByBookId(id, value);
            case 7:
                return setBookDateByBookId(id, value);
            case 8:
                return setBookSalesByBookId(id, value);
            case 9:
                return setBookStockByBookId(id, Integer.valueOf(value));
            case 10:
                return setBookCoverByBookId(id, value);
            case 11:
                return setBookIntroByBookId(id, value);
            default:
                return 0;
        }
    }


    public Book addBookByIsbn(String isbn)
    {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setStock(0);
        book.setStatus("Unavailable");
        book.setAuthor("Unknown Author");
        book.setPrice(999.0);
        book.setLanguage("Unknown Language");
        book.setName("Unknown Name");
        Book newBook = bookRepository.save(book);
        BookColl bookColl  = new BookColl(newBook.getBookId(), "", "No intro yet.");
        bookCollRepository.save(bookColl);
        return findBookByBookId(newBook.getBookId());
    }

    public Book addBookByName(String name)
    {
        Book book = new Book();
        book.setName(name);
        book.setStock(0);
        book.setStatus("Unavailable");
        book.setAuthor("Unknown Author");
        book.setPrice(999.0);
        book.setLanguage("Unknown Language");
        book.setIsbn("Unknown ISBN");
        Book newBook = bookRepository.save(book);
        BookColl bookColl  = new BookColl(newBook.getBookId(),"", "No intro yet.");
        bookCollRepository.save(bookColl);
        return findBookByBookId(newBook.getBookId());
    }

    public Optional<Book> findBookByName(String name)
    {
        return bookRepository.findBookByNameEquals(name);
    }

    public Optional<Book> findBookByIsbn(String isbn)
    {
        return bookRepository.findBookByIsbnEquals(isbn);
    }

    public Integer setBookIsbnByBookId(Integer id, String isbn){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setIsbn(isbn);
        return bookRepository.save(book).getIsbn().equals(isbn) ?1:0;
    }

    public Integer setBookNameByBookId(Integer id, String name){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setName(name);
        return bookRepository.save(book).getName().equals(name) ?1:0;
    }

    public Integer setBookCategoryByBookId(Integer id, String category){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setCategory(category);
        return bookRepository.save(book).getCategory().equals(category) ?1:0;
    }

    public Integer setBookAuthorByBookId(Integer id, String author){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setAuthor(author);
        return bookRepository.save(book).getAuthor().equals(author) ?1:0;
    }

    public Integer setBookPriceByBookId(Integer id, Double price){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setPrice(price);
        return bookRepository.save(book).getPrice().equals(price) ?1:0;
    }

    public Integer setBookLanguageByBookId(Integer id, String language){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setLanguage(language);
        return bookRepository.save(book).getLanguage().equals(language) ?1:0;
    }

    public Integer setBookDateByBookId(Integer id, String date){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setDate(date);
        return bookRepository.save(book).getDate().equals(date) ?1:0;
    }

    public Integer setBookSalesByBookId(Integer id, String sales){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setSales(sales);
        return bookRepository.save(book).getSales().equals(sales) ?1:0;
    }

    public Integer setBookStatusByBookId(Integer id, String status){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setStatus(status);
        return bookRepository.save(book).getStatus().equals(status) ?1:0;
    }

    public Integer setBookStockByBookId(Integer id, Integer stock){
        Book book = bookRepository.findBookByBookIdEquals(id).get();
        book.setStock(stock);
        if(stock == 0 && book.getStatus().equals("Available"))
            book.setStatus("Unavailable");
        else if(stock > 0 && book.getStatus().equals("Unavailable"))
            book.setStatus("Available");

        return bookRepository.save(book).getStock().equals(stock) ?1:0;
    }

    public Integer setBookCoverByBookId(Integer id, String cover){
        BookColl bookColl = bookCollRepository.findBookCollByBookIdEquals(id).get();
        bookColl.setBookCover(cover);
        return bookCollRepository.save(bookColl).getBookCover().equals(cover) ?1:0;
    }

    public Integer setBookIntroByBookId(Integer id, String intro){
        BookColl bookColl = bookCollRepository.findBookCollByBookIdEquals(id).get();
        bookColl.setBookIntro(intro);
        return bookCollRepository.save(bookColl).getBookIntro().equals(intro) ?1:0;
    }
}
