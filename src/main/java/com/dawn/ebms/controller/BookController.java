package com.dawn.ebms.controller;

import com.dawn.ebms.entity.Book;
import com.dawn.ebms.entity.PurchaseItem;
import com.dawn.ebms.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController extends HttpServlet {

    @Autowired

    private BookService bookService;

    private List<Book> bookList;

    @PostConstruct
    public void initialize()
    {
        bookList = getBooks();
    }

    @GetMapping(value = "/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(value = "/getBooksBy")
    public List<Book> findBookBySearch(@RequestParam("search") String search)
    {
        return bookService.findBookBySearch(search);
    }

    @GetMapping(value = "/getBook")
    public Book findBookById(@RequestParam("bookId") Integer id)
    {
        System.out.println("Searching Book:" + id);
        return bookService.findBookByBookId(id);
    }

    @RequestMapping(value = "/getBookByNames")
    public List<Integer> findBookByNames(@RequestParam("books") String books) throws JsonProcessingException{
        System.out.println(books);
        System.out.println("In getBookByNames");
        return bookService.findBookByNames(books);
    }

    @RequestMapping(value = "/addBookByIsbn")
    public Book addBookByIsbn(@RequestParam("isbn")String isbn)
    {
        return bookService.addBookByIsbn(isbn);
    }

    @RequestMapping(value = "/addBookByName")
    public Book addBookByName(@RequestParam("name")String name)
    {
        return bookService.addBookByName(name);
    }

    @RequestMapping(value = "/deleteBook")
    public void deleteBookByBookId(@RequestParam("bookId") Integer id)
    {
        bookService.deleteBookByBookId(id);
    }

    @RequestMapping(value = "/updateBook")
    public Integer updateBook(@RequestParam("setType") Integer type, @RequestParam("bookId") Integer id, @RequestParam("value") String value)
    {
        System.out.println(type);
        System.out.println(id);
        System.out.println(value);
        return bookService.updateBook(type, id, value);
    }

    // Usage of AJAX
    @RequestMapping(value="/hint")
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        String hint = "";

        System.out.println("Hint invoked!");

        try {
            String qTmp = request.getParameter("q");
            String q = qTmp.toLowerCase();
            if (q.length() > 0) {
                for (int i = 0; i < bookList.size(); i++)
                    if (bookList.get(i).getName().toLowerCase().contains(q)) {
                        System.out.println(bookList.get(i).getName().indexOf(q));
                        if (hint == "")
                            hint = bookList.get(i).getName();
                        else
                            hint = hint + ", " + bookList.get(i).getName();
                    }
            }

            if (hint == "") {
                out.println("Our librarian hasn't sorted them yet...");
            } else {
                String[] hintArr = hint.split(",");
                JSONArray jsonArray = new JSONArray(hintArr);
                out.println(jsonArray.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    }
