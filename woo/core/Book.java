package woo.core;

public class Book extends Product {
	private String _title;
	private String _author;
	private String _isbn;

	public Book(String id, String title, String author, String isbn, int price, int crit, Supplier supplier) {
		super(id, price, crit, supplier);
		_title = title;
		_author = author;
		_isbn = isbn;
	}

	@Override
	public String toString() {
		return "BOOK|" + getId() + "|" + getSupplier().getId() + "|" + getPrice(); + "|" +
		getCriticalValue() + "|" + getCurrentQuantity() + "|" + _title + "|" + _author + "|" + _isbn;
	}
}