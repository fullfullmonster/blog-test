package com.example.blog.service;

import com.example.blog.po.Type;
import org.springframework.data.domain.Page;
import sun.util.resources.ga.LocaleNames_ga;

import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {

    void addType(Type type);

    Type findTypeById(Long id);

    Type findTypeByName(String name);

    List<Type> findBlogType();

    List<Type> findAllType();

    void updateType(Type type);

    void deleteType(Long id);


}
