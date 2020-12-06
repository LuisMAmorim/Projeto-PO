package woo.core;

import woo.core.exception.InvalidPriceException;

public class Book extends Product {
	private String _title;
	private String _author;
	private String _isbn;

	public Book(String id, String title, String author, String isbn, Supplier supplier, int price, int crit, int q)	{
		super(id, supplier, price, crit, q);
		_title = title;
		_author = author;
		_isbn = isbn;
	}

	public String getProductType() {
		return "BOOK";
	}

	public String getExtraInformation() {
		return String.format("%s|%s|%s", _title, _author, _isbn);
	}
}