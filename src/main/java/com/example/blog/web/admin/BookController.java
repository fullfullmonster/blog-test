package com.example.blog.web.admin;

import com.example.blog.po.Blog;
import com.example.blog.po.Book;
import com.example.blog.po.Type;
import com.example.blog.po.User;
import com.example.blog.service.BookService;
import com.example.blog.service.BookServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/book")
    public String books(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 8);
        List<Book> books = bookService.findAllBook();
        PageInfo<Book> lists = new PageInfo<>(books);
        model.addAttribute("pages",lists);
        return "admin/book";
    }

    @PostMapping("/book/search")
    public String searchBook(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                             Model model,@RequestParam String bookName) {
        PageHelper.startPage(pageNum, 8);
        List<Book> books = bookService.searchBookByName(bookName);
        PageInfo<Book> lists = new PageInfo<>(books);
        model.addAttribute("pages",lists);
        return "admin/book";
    }

    @GetMapping("/book/input")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "admin/book-input";
    }


    @GetMapping("/book/{id}/input")
    public String updateBook(@PathVariable int id, Model model){
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        return "admin/book-input";
    }

    @PostMapping("/book")
    public String post(Book book, RedirectAttributes attributes){

        if(book.getId() == null){
            attributes.addFlashAttribute("message","新增图书成功");
            bookService.saveBook(book);
        }else {
            bookService.updateBook(book);
            attributes.addFlashAttribute("message","编辑图书成功");
        }

        return "redirect:/admin/book";
    }

    @GetMapping("/book/{id}/delete")
    public String deleteBook(@PathVariable int id, RedirectAttributes attributes){
        bookService.deleteBook(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/book";
    }
}
