package com.spn.repository;

import java.util.List;

import com.spn.Model.Book;

public interface BookRepository {
	public List<Book> findAll();

	public Book findById(int id);

	public boolean save(Book book);

	public boolean update(Book book);

	public boolean remove(Integer id);
}