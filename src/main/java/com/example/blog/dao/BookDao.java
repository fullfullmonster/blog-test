package com.example.blog.dao;

import com.example.blog.po.Blog;
import com.example.blog.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
@Mapper
public interface BookDao {

    Book findBookById(long id);

    Book findDetailBook(@Param("id") long id);

    List<Book> findAllBook();

    List<Book> searchBookByName(String name);


    int saveBook(Book book);

    int updateBook(Book book);

    int deleteBook(int id);
}
