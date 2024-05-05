package com.innobyteservices.librarymanagement.admin.dao;

import java.util.List;

import com.innobyteservices.librarymanagement.admin.models.Books;

public interface IBooksDAO {

	List<Books> getAllBooks();

	Books addBooks(Books books);

	Books updateBooks(Books books);

	List<Books> getBooksById(int bookId);

}
