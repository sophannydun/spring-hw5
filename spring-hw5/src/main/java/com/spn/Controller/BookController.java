package com.spn.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spn.Model.Book;
import com.spn.Service.BookService;
import com.spn.Service.BookServiceImp;
import com.spn.Service.FileUploadService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = { "/", "/index", "/home" })
	public String allBook(Model model) {
		System.out.println("home controller");
		List<Book> listBook = bookService.findAll();
		model.addAttribute("listBook", listBook);
		return "index";
	}

	@RequestMapping(value = "/book/{id}")
	public String bookDetail(@PathVariable("id") Integer id, Model model) {
		Book book = bookService.findById(id);
		model.addAttribute("bookdetail", book);
		return "bookDetail";
	}

	@RequestMapping(value = "/book/add")
	public String addBook(Model model) {
		model.addAttribute("addStatus", true);
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@RequestMapping( value = "/book/add", method = RequestMethod.POST)
	public String actionAddBook(@RequestParam("file") MultipartFile file, Model model,@Valid Book book,BindingResult result) {
			if(result.hasErrors()){
				for (FieldError error : result.getFieldErrors()){
					System.out.println(error.getField());
					model.addAttribute("addStatus", true);
					model.addAttribute("book", book);
					return "/addBook";
				}
			}
			String filePath=fileUploadService.fileUpload(file);
			book.setCoverImage(filePath);
			System.out.println(filePath);
		/*if(!file.isEmpty()){	
			String filePath=fileUploadService.fileUpload(file);
			book.setCoverImage(filePath);
			System.out.println(filePath);
		}
		*/
		boolean b = bookService.save(book);
		return "redirect:/index";
	}

	@RequestMapping(value = "/book/edit")
	public String editBook(Model model, @RequestParam("id") Integer id) {
		System.out.println("Id=" + id);
		Book book = bookService.findById(id);
		model.addAttribute("book", book);
		model.addAttribute("addStatus", false);
		return "addBook";
		// return to page can send data to view layer
	}

	@RequestMapping(value = "/book/update")
	public String updateBook(Book book) {
		System.out.println(book.toString());
		bookService.update(book);
		return "redirect:/index";
	}

	@RequestMapping(value = "/book/remove")

	@PostMapping(value = "/book/remove")
	public String remove(@RequestParam("id") Integer id) {
		System.out.println("Id=" + id);
		bookService.remove(id);
		return "redirect:/index";
		// redirect to mapped url above controller index mapping can not send
		// data to view layer
	}

}
