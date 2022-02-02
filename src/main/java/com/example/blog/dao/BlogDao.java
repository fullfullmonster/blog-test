package com.example.blog.dao;

import com.example.blog.po.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

    Blog findBlogById(Long id);  //后台展示博客

    Blog findDetailedBlog(@Param("id") Long id);  //博客详情

    List<Blog> findAllBlog(Long user_id);

    List<Blog> findAllUserBlog();//首页博客

    List<Blog> findByTypeId(Long typeId);  //根据类型id获取博客

    List<Blog> findIndexBlog();  //主页博客展示

    List<Blog> findAllRecommendBlog();  //推荐博客展示

    List<Blog> findAllPublishedBlog();  //推荐博客展示

    List<Blog> findSearchBlog(String query);  //全局搜索博客

    //    根据博客id查询出评论数量
    int findCommentCountById(Long id);


    List<String> findGroupYearAndMonth();  //查询所有年份，返回一个集合

    List<Blog> findByYearAndMonth(@Param("year") String year);  //按年份查询博客

    List<Blog> searchBlogByTitleAndType(String title, Long id);

    List<Blog> searchBlogByTitle(String title);

    List<Blog> searchBlogByType(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);
}
