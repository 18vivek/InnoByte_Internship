package com.innobyteservices.librarymanagement.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innobyteservices.librarymanagement.admin.dao.IBooksDAO;
import com.innobyteservices.librarymanagement.admin.models.Books;

@Service
public class BooksServiceImpl implements IBooksService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BooksServiceImpl.class);
	
	@Autowired
	private IBooksDAO booksDao;

	@Override
	public List<Books> getAllBooks() {
		try {
			return booksDao.getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while retrieving Books list", e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Books> getBookById(int bookId) {
		try {
			return booksDao.getBooksById(bookId);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while retrieving Book list", e.getMessage());
		}
		return null;
	}

	@Override
	public Books addBooks(Books books) {
		Books book = null;
		try {
			return booksDao.addBooks(books);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while adding Book Details for "+ books.getBookName()+ "", e.getMessage());
		}
		return book;
	}

	@Override
	public Books updateBooks(Books books) {
		try {
			return booksDao.updateBooks(books);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred in updateBooks() ", e.getMessage());
		}
		return null;
	}

}
