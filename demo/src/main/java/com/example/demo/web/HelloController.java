package com.example.demo.web;

import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/v1")
public class HelloController {

    @Autowired
    private Book book;

    @GetMapping("/say")
    public String hello() {
        return "Hello Spring1";
    }

    @GetMapping("/books")
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", "羅馬帝國衰亡史");
        book.put("isbn", "6454545456");
        book.put("aithor", "Tony");
        
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "反脆弱");
        book2.put("isbn", "9455811223");
        book2.put("author", "Anna");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("content", contents);

        return pagemap;
//          return "books";
    }

    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {
//        System.out.println("id: " + id + " username: " + username);

//        Map<String, Object> book = new HashMap<>();
//        book.put("name", name);
//        book.put("ISBN", isbn);
//        book.put("author", author);
//        book.put("description", description);
        return book;
    }

    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam("author") String author,
                       @RequestParam("isbn") String isbn){
        Map<String, Object> book = new HashMap<String, Object>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);
        return book;
    }

}



