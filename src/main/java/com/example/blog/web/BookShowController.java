package com.example.blog.web;

import com.example.blog.po.Book;
import com.example.blog.po.Comment;
import com.example.blog.service.BookService;
import com.example.blog.service.BookServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookShowController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/book")
    public String books(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 6);
        List<Book> books = bookService.findAllBook();
        PageInfo<Book> lists = new PageInfo<>(books);
        model.addAttribute("pages",lists);
        return "book";
    }

    @GetMapping("/book/{id}")
    public String bookDetail(@PathVariable long id, Model model){
        model.addAttribute("book",bookService.findDetailBook(id));
        return "bookDetail";
    }
}
