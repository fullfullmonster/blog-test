package com.example.blog.service;

import com.example.blog.po.Book;

import java.util.List;

public interface BookService {

    Book findBookById(int id);

    List<Book> findAllBook();

    List<Book> searchBookByName(String name);


    int saveBook(Book book);

    int updateBook(Book book);

    int deleteBook(int id);

    Book findDetailBook(long id);

}
