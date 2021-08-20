package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositroy  extends JpaRepository<Book, Long> {
}
