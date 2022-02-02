package com.example.blog.service;

import com.NotFoundException;
import com.example.blog.dao.BlogDao;
import com.example.blog.po.Blog;
import com.example.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog findBlogById(Long id) {
        return blogDao.findBlogById(id);
    }

    @Override
    public List<Blog> findAllBlog(Long type_id) {

        return blogDao.findAllBlog(type_id);
    }

    @Override
    public List<Blog> findAllUserBlog() {

        return blogDao.findAllUserBlog();
    }

    @Override
    public Blog findDetailedBlog(Long id) {
        Blog blog = blogDao.findDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<Blog> findByTypeId(Long type_id) {
        return blogDao.findByTypeId(type_id);
    }

    @Override
    public List<Blog> findSearchBlog(String query) {
        return blogDao.findSearchBlog(query);
    }

    @Override
    public List<Blog> findIndexBlog() {
        return blogDao.findIndexBlog();
    }

    @Override
    public List<Blog> findAllRecommendBlog() {
        return blogDao.findAllRecommendBlog();
    }

    @Override
    public List<Blog> findAllPublishedBlog() {
        return blogDao.findAllPublishedBlog();
    }


    @Override
    public void saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogDao.saveBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogDao.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> Search(String title, Long type,Long user_id) {
        if (title.trim().isEmpty()) {
            if (type != null) {
                return blogDao.searchBlogByType(type);
            } else {
                return blogDao.findAllBlog(user_id);
            }
        } else if (type != null) {
            return blogDao.searchBlogByTitleAndType(title, type);
        } else if (type == null){
            return blogDao.searchBlogByTitle(title);
        }
        return null;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYearAndMonth();
        Set<String> set = new HashSet<>(years);
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYearAndMonth(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.findAllUserBlog().size();
    }

}
