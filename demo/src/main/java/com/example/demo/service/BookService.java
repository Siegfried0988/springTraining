package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepositroy bookRepositroy;

//    查詢所有的書單列表
    public List<Book> findAll(){
        return bookRepositroy.findAll();
    }

//    提交一條讀書清單(可以是新增也可以是更新)
    public Book save(Book book){
        return bookRepositroy.save(book);
    }

//    獲取一條書單信息
    public Book findOne(long id){
//        return bookRepositroy.findOne(id);
        return bookRepositroy.findById(id).orElse(null);
    }


//    刪除一條書單信息
    public void  delete(long id){
        bookRepositroy.deleteById(id);
    }


//    根據author來查詢一個書單列表
    public List<Book> findByAuthor(String author){
        return  bookRepositroy.findByAuthor(author);
    }

//    根據author跟status查詢一個書單
    public List<Book> findByAuthorAndStatus(String author, int status){
        return bookRepositroy.findByAuthorAndStatus(author, status);
    }




    public List<Book> findByDescriptionContains(String des){
        return bookRepositroy.findByDescriptionContains(des);
    }


//    自定義查詢
    public List<Book> findByJPQL(int len){
        return bookRepositroy.findByJPQL(len);
    }


    public int updateByJPQL(int status, long id){
        return bookRepositroy.updateByJPQL(status, id);
    }


//    自定義刪除
    @Transactional
    public int deleteByJPQL(long id){
        return bookRepositroy.deleteByJPQL(id);
    }


//    刪除+修改
//    @Transactional
    public  int deleteAndUpdate(long id, int status, long uid){
        int dcount = bookRepositroy.deleteByJPQL(id);
        int ucount = bookRepositroy.updateByJPQL(status, uid);
//        int ucount = 0;
        return  dcount+ucount;
    }



}
