package com.innobyteservices.librarymanagement.admin.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.innobyteservices.librarymanagement.admin.models.Books;

public class BooksRowMapper implements RowMapper<Books>  {

	@Override
	public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
		Books book = new Books();
        book.setBookId(rs.getInt("bookId"));
        book.setBookName(rs.getString("bookName"));
        book.setAuthorName(rs.getString("authorName"));
        book.setCost(rs.getDouble("cost"));
        book.setQuantity(rs.getInt("quantity"));
        return book;
	}

}
