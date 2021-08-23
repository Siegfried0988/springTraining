package com.example.demo.web;

import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

//    獲取讀書清單列表
    @GetMapping("/books")
    public List<Book> getAll(){

        return bookService.findAll();
    }
//    public List<Book> getAll1(){
//
//        return bookService.findAll();
//    }

    @PostMapping("/books")
    public Book post(@RequestParam String name, @RequestParam String author, @RequestParam String description, @RequestParam int status){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

//    @PostMapping("/books")
//    public Book post(Book book){
//
//        return bookService.save(book);
//    }

//    獲取一條書單信息
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id){

        return bookService.findOne(id);

    }



    //    提交一條讀書清單
    @PutMapping("/books")
    public Book update(@RequestParam Long id, @RequestParam String name, @RequestParam String author, @RequestParam String description, @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    //    刪除一條書單信息
    @DeleteMapping("/books/{id}")
    public void  deleteOne(@PathVariable long id){
        bookService.delete(id);

    }

    @PostMapping("/books/by")
    public int findBy(@RequestParam long id, @RequestParam int status, @RequestParam long uid){
//        return bookService.findByAuthor(author);
//        return bookService.findByAuthorAndStatus(author, status);
//        return bookService.findByDescriptionContains(description);

//        return bookService.findByJPQL(len);
//        return bookService.updateByJPQL(status, id);
//        return bookService.deleteByJPQL(id);
        return bookService.deleteAndUpdate(id, status, uid);
    }
}
