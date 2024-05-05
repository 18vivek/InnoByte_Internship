package com.innobyteservices.librarymanagement.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.innobyteservices.librarymanagement.admin.mapper.BooksRowMapper;
import com.innobyteservices.librarymanagement.admin.models.Books;

@Repository
public class BooksDAOImpl implements IBooksDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(BooksDAOImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Books> getAllBooks() {
		String sql = "SELECT * FROM library.books";
		List<Books> books = null;
		try {
			books = jdbcTemplate.query(sql, new BooksRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
	@Override
	public List<Books> getBooksById(int bookId) {
		
		List<Books> books = null;
		try {
			String sql = "SELECT * FROM library.books where bookId =:bookId";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("bookId", bookId);
			books = jdbcTemplate.query(sql,args, new BooksRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Unable to fetch details with this bookId: "+bookId);
		}
		return books;
	}

	@Override
	public Books addBooks(Books books) {
		final String sql = "INSERT INTO library.books (bookName, authorName, cost, quantity) \r\n"
				+ "VALUES(:bookName, :authorName, :cost, :quantity);";

		try {

			MapSqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("bookName", books.getBookName())
					.addValue("authorName", books.getAuthorName())
					.addValue("cost", books.getCost())
					.addValue("quantity", books.getQuantity());
			final KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(sql, parameters, holder, new String[] { "bookId" });
			Number generatedId = holder.getKey();
			books.setBookId(generatedId.intValue()); 

			LOGGER.info("record inserted with ID {}", generatedId.intValue());

		} catch (Exception e) {
			LOGGER.error("Error while inserting Book details.", e.getMessage());
		}

		return books;
	}

	@Override
	public Books updateBooks(Books books) {
		final String sql ="UPDATE library.books SET bookName =:bookName, authorName =:authorName, cost =:cost, quantity =:quantity\r\n"
				+ "WHERE bookId =:bookId;";
		
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("bookName", books.getBookName());
			args.put("authorName", books.getAuthorName());
			args.put("cost", books.getCost());
			args.put("quantity", books.getQuantity());
			args.put("bookId", books.getBookId());
			

			int i = jdbcTemplate.update(sql, args);
			LOGGER.info("record updated {}", i);
		} catch (Exception e) {
			LOGGER.error("Error while updating the Book details..", e.getMessage());
		}
		return books;
	}

}
