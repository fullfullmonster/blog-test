package com.example.blog.web.admin;

import com.example.blog.po.Blog;
import com.example.blog.po.Type;
import com.example.blog.po.User;
import com.example.blog.service.BlogServiceImpl;
import com.example.blog.service.TypeServiceImpl;
import com.example.blog.service.UserServiceImpl;
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
public class BlogController {

    private static final String INPUT = "admin/blog-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT = "redirect:/admin/blogs";


    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl  typeService;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/blogs")
    public String blogs(HttpSession session,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 8);
        Long userId = ((User) session.getAttribute("user")).getId();
        List<Blog> allBlog = blogService.findAllBlog(userId);
        PageInfo<Blog> lists = new PageInfo<>(allBlog);
        model.addAttribute("pages", lists);
        model.addAttribute("types", typeService.findAllType());
        return LIST;

      }

    @PostMapping("/blogs/search")
    public String searchBlog(HttpSession session,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                             Model model,@RequestParam String title,@RequestParam Long typeId) {
        PageHelper.startPage(pageNum, 8);
        Long userId = ((User) session.getAttribute("user")).getId();
        List<Blog> blogs = blogService.Search(title,typeId,userId);
        PageInfo<Blog> lists = new PageInfo<>(blogs);
        model.addAttribute("pages", lists);
        List<Type> types = typeService.findAllType();
        model.addAttribute("types", types);

        return LIST;
    }


    @GetMapping("/blogs/input") //?????????????????????
    public String input(Model model){
        model.addAttribute("blog", new Blog());  //????????????blog???????????????th:object
        model.addAttribute("types", typeService.findAllType());
        return INPUT;
    }


    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());

        //??????blog???type

        blog.setType(typeService.findTypeById(blog.getTypeId()));
        //??????blog???typeId??????
         blog.setTypeId(blog.getTypeId());

        if(blog.getId() == null){
           attributes.addFlashAttribute("message","??????????????????");
           blogService.saveBlog(blog);
       }else {
           blogService.updateBlog(blog);
           attributes.addFlashAttribute("message","??????????????????");
       }

        return REDIRECT;
    }

    @GetMapping("/blogs/{id}/input") //?????????????????????
    public String toEditBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.findBlogById(id);
        model.addAttribute("blog", blog);     //????????????blog???????????????th:object
        model.addAttribute("types", typeService.findAllType());
        return "admin/blog-input";
    }
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "????????????");
        return "redirect:/admin/blogs";
    }
}
