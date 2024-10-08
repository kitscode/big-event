package com.husky.service;


import com.husky.pojo.Article;
import com.husky.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //根据id查找对应文章
    Article findById(Integer id);

    void update(Article article);

    void delete(Integer id);
}
