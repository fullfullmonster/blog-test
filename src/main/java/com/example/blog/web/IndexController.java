package com.example.blog.web;


import com.example.blog.po.Blog;
import com.example.blog.po.Comment;
import com.example.blog.po.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.example.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String toIndex(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

        PageHelper.startPage(pagenum, 8);
        List<Blog> allBlog = blogService.findIndexBlog();
       List<Type> allType = typeService.findBlogType();  //获取博客的类型(联表查询)
        List<Blog> recommendBlog =blogService.findAllRecommendBlog();  //获取推荐博客

        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pages", pageInfo);
        model.addAttribute("types", allType);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query,Model model){
        PageHelper.startPage(pagenum, 8);
        List<Blog> blogs = blogService.findSearchBlog(query);
        PageInfo pages = new PageInfo(blogs);
        model.addAttribute("pages", pages);
        model.addAttribute("query",query);

        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable long id, Model model){
        model.addAttribute("blog",blogService.findDetailedBlog(id));
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
