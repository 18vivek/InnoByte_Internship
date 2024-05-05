package com.innobyteservices.librarymanagement.admin.service;

import java.util.List;

import com.innobyteservices.librarymanagement.admin.models.Books;

public interface IBooksService {

	List<Books> getAllBooks();

	Books addBooks(Books books);

	Books updateBooks(Books books);

	List<Books> getBookById(int bookId);

}
