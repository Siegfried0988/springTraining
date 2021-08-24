package com.example.demo.domain;


import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //    獲取書單列表
    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
//        List<Book> books = bookService.findAll();

//        Sort sort = new Sort(Sort.Direction.DESC, "id"); 相同
//        Pageable pageable = new PageRequest(1,5, sort);
//        Pageable pageable = PageRequest.of(1, 5, Sort.Direction.DESC, "id");
        Page<Book> page1 = bookService.findAllByPage(pageable);
        model.addAttribute("page", page1);
        return "books";
    }

    //    獲取書單詳情
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    //    跳轉input提交頁面
    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }


    //  跳轉到更新頁面
    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "input";
    }

    //    因為提交書單的程式是POST->redirect->GET這樣的傳送順序，中間經過了redirect還變化取值方式
//    所以如果用普通的方式傳值會傳不過去。這時候就要改成RedirectAttributes attributes的寫法
//    提交一個書單信息
    @PostMapping("/books")
    public String post(Book book, RedirectAttributes attributes) {
        Book book1 = bookService.save(book);
        if (book1 != null) {
            attributes.addFlashAttribute("message", "《" + book1.getName() + "》信息提交成功");
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, final RedirectAttributes attributes){
        bookService.delete(id);
        attributes.addFlashAttribute("message", "刪除成功");
        return "redirect:/books";

    }
}
