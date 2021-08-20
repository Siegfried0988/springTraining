package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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
}
