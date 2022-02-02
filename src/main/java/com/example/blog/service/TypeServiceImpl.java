package com.example.blog.service;

import com.example.blog.dao.TypeDao;
import com.example.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public void addType(Type type) {

        typeDao.addType(type);
    }

    @Transactional
    @Override
    public List<Type> findAllType() {
        return typeDao.findAllType();
    }

    @Transactional
    @Override
    public Type findTypeById(Long id) {
        return typeDao.findTypeById(id);
    }

    @Transactional
    @Override
    public Type findTypeByName(String name){
        return typeDao.findTypeByName(name);
    }

    @Transactional
    @Override
    public void updateType(Type type){
        typeDao.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
            typeDao.deleteType(id);
    }

    @Override
    public List<Type> findBlogType() {
        return typeDao.findBlogType();
    }
}
