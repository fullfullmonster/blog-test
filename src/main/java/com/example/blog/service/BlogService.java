package com.example.blog.service;

import com.example.blog.po.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog findBlogById(Long id);

    List<Blog> findAllBlog(Long user_id);

    List<Blog> findAllUserBlog();

    Blog findDetailedBlog(Long id);
    List<Blog> findByTypeId(Long type_id);
    List<Blog> findSearchBlog(String query);
    List<Blog> findIndexBlog();
    List<Blog> findAllRecommendBlog();
    List<Blog> findAllPublishedBlog();
    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> Search(String title, Long type,Long user_id);

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int countBlog();

}
