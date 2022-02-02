package com.example.blog.dao;

import com.example.blog.po.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    int addType(Type type);

    Type findTypeByName(String name);

    Type findTypeById(Long id);

    List<Type> findAllType();

    void updateType(Type type);

    void deleteType(Long id);

    List<Type> findBlogType();
}
