package com.example.blog.service;

import com.NotFoundException;
import com.example.blog.dao.BookDao;
import com.example.blog.po.Blog;
import com.example.blog.po.Book;
import com.example.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> findAllBook() {
        return bookDao.findAllBook();
    }

    @Override
    public List<Book> searchBookByName(String name) {
        if(name.trim().isEmpty()){
           return bookDao.findAllBook();
        }
        else {
            return bookDao.searchBookByName(name);
        }

    }

    @Override
    public int saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public Book findDetailBook(long id) {
        Book book = bookDao.findDetailBook(id);
        if (book == null) {
            throw new NotFoundException("该图书不存在");
        }
        String content = book.getContent();
        book.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return book;
    }
}
