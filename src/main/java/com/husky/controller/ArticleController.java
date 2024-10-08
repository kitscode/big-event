package com.husky.controller;

import com.husky.pojo.Article;
import com.husky.pojo.PageBean;
import com.husky.pojo.Result;
import com.husky.service.ArticleService;
import com.husky.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /*@GetMapping("/list")
    public Result<String> list() {
        return Result.success("article list..");
    }*/

    @PostMapping
    public Result add(@RequestBody @Validated(Article.Add.class) Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list (Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Article> detail (Integer id) {
        Article a = articleService.findById(id);
        return Result.success(a);
    }

    @PutMapping
    public Result update (@RequestBody @Validated(Article.Update.class) Article article) {
        Integer id = article.getId();
        Article articleFindById = articleService.findById(id);
        Integer createUserId = articleFindById.getCreateUser();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        if (!(createUserId == userId)) {
            return Result.error("当前用户并非此文章的创建者");
        }
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id) {
        Article articleById = articleService.findById(id);
        if (articleById == null) {
            return Result.error("当前文章id不存在");
        }
        Integer articleCreateUserId = articleById.getCreateUser();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer nowUserId = (Integer) map.get("id");
        if (!(articleCreateUserId == nowUserId)) {
            return Result.error("当前用户并非文章的创建者");
        }
        articleService.delete(id);
        return Result.success();
    }


}
