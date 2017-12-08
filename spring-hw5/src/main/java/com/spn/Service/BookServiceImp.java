package com.spn.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.spn.Model.Book;
import com.spn.repository.BookRepository;

@Service
public class BookServiceImp implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(int id) {
		System.out.println(bookRepository.findById(id).toString());
		return bookRepository.findById(id);
	}

	@Override
	public boolean save(Book book) {
		boolean status = bookRepository.save(book);
		if (status) {
			System.out.println("Book added Successfully.");
			return false;
		} else {
			System.out.println("Book cannot add.");
			return true;
		}

	}

	@Override
	public boolean update(Book book) {
		if (bookRepository.update(book)) {
			System.out.println("Book updated Successfully.");
			return true;
		} else {
			System.out.println("Book update failed.");
			return true;
		}
	}

	@Override
	public boolean remove(int id) {
		if (bookRepository.remove(id)) {
			System.out.println("Book has been removed Successfully.");
			return true;
		} else {
			System.out.println("Book remove failed.");
			return false;
		}
	}
}
