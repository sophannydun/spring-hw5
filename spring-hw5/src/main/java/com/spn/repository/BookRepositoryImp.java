package com.spn.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import com.github.javafaker.Faker;
import com.spn.Model.Book;
@Repository

//@Repository mena this class is repository class it use with CRUD
//operation it will create a bean in containner
public class BookRepositoryImp implements BookRepository {
	
	private List<Book> listBook = new ArrayList<>();

		
	public BookRepositoryImp() {
		super();
		Faker faker=new Faker();
			Book book=new Book();
			for (int i = 0; i < 10; i++) {
				/*int id=faker.number().numberBetween(1, 100);*/
				/*int pageNo=faker.number().numberBetween(200, 1000);*/
				/*int num=(faker.number().numberBetween(1990, 2017));*/
				int id=i+1;
				String name = faker.book().title().toString();
				String authorName=faker.book().author().toString();
				String coverImage = faker.internet().image(1920, 1080, false, null);
				int pageNo=200 + i * 23;
				String authorDate=Integer.toString(1990 + i +10);
						book= new Book(id,name,authorDate,authorName,pageNo,coverImage);
				
				listBook.add(book);
			}

	}

	@Override
	public List<Book> findAll() {
		return listBook;
	}

	@Override
	public Book findById(int id) {
		for (Book book : listBook) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	@Override
	public boolean save(Book book) {
		return listBook.add(book);
	}

	@Override
	public boolean update(Book book) {
		for(int i=0;i<listBook.size();i++){
			if(listBook.get(i).getId()==book.getId()){
				listBook.set(i, book);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) {
		for(int i=0;i<listBook.size();i++){
			if(listBook.get(i).getId()==id){
				listBook.remove(i);
				return true;
			}
		}
		return false;
	}

}
