package com.innobyteservices.librarymanagement.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innobyteservices.librarymanagement.ResponseObject;
import com.innobyteservices.librarymanagement.admin.models.Books;
import com.innobyteservices.librarymanagement.admin.service.IBooksService;

@RestController
@RequestMapping("/admin")
public class BooksController {
	
	@Autowired
	private IBooksService booksService;
	
	@GetMapping("/getAllBooks")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllBooks() {
		final ResponseObject response = new ResponseObject();

		try {
			List<Books> books = booksService.getAllBooks();
			response.setData(books);
			response.setMessage(books.size()+" record(s) fetched.");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			final List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			response.setErrorMessages(errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@GetMapping("/getBookById/{bookId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getBookById(@PathVariable("bookId") int bookId) {
		final ResponseObject response = new ResponseObject();
		if (bookId <= 0) {
			final List<String> errors = new ArrayList<String>();
			errors.add("Invalid BookId.");
			response.setErrorMessages(errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		try {
			List<Books> books = booksService.getBookById(bookId);
			response.setData(books);
			response.setMessage(books.size()+" record(s) fetched.");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			final List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			response.setErrorMessages(errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@PostMapping(value = "/addBooks")
	@ResponseBody
	public ResponseEntity<ResponseObject> addBooks(@RequestBody Books books) {

//		final List<String> errorsList = clientValidator.validate(client);

		final ResponseObject response = new ResponseObject();
		
		try {
			Books addedBooks = booksService.addBooks(books);
			response.setMessage("1 record(s) added.");
			response.setData(addedBooks);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			final List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			response.setErrorMessages(errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	@PostMapping(value = "/updateBooks")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateClient(@RequestBody Books books) {
//		final List<String> errorsList = clientValidator.validate(client);
		final ResponseObject response = new ResponseObject();
		try {
			Books updatedBooks = booksService.updateBooks(books);
			response.setMessage("1 record(s) updated.");
			response.setData(updatedBooks);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			final List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			response.setErrorMessages(errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
