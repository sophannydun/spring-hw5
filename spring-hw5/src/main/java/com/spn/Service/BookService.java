package com.spn.Service;

import java.util.List;

import com.spn.Model.Book;

public interface BookService {
	public List<Book> findAll();

	public Book findById(int id);

	public boolean save(Book book);

	public boolean update(Book book);

	public boolean remove(int id);
}
