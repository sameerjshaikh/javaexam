package basics;

import java.util.*;
import java.lang.*;
class Book{
	@Override
	public String toString() {
		return String.format("The Book:%s\nThe Cost:%f", title, price);
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	private int bookId;
	private String title;
	private double price;
	
	public Book(int id, String title, double amount) {
		this.bookId = id;
		this.title = title;
		this.price = amount;
	}
	
}

class BookStore implements Iterable<Book>{
	private List<Book> _books = new ArrayList<Book>();
	public void addBook(Book bk) {
		_books.add(bk);
	}
	
	public void updateBook(Book bk) throws Exception {
		for(int i =0; i < _books.size();i++) {
			Book temp = _books.get(i);
			if(temp.getBookId() == bk.getBookId()) {
				_books.add(i, bk);
			}
		}
		throw new Exception("Book not found to update");
	}
	
	public void deleteBook(int id) throws Exception {
		for (int i = 0; i < _books.size(); i++) {
			if(_books.get(i).getBookId() == id) {
				_books.remove(i);
				return;
			}
		}
		throw new Exception("Book not found to delete");
	}
	
	public List<Book>Find(String title){
		List<Book>temp = new ArrayList<Book>();
		for (Book book : _books) {
			if(book.getTitle().contains(title))
				temp.add(book);
		}
		return temp;
	}

	@Override
	public Iterator<Book> iterator() {
		return _books.iterator();
	}
}
//What makes a collection class? A Class whose object can be used in a foreach statement is what makes a collection class. In other words, the object should allow to access one element at a time without going out of bounds in it. 
public class CustomCollection {

	public static void main(String[] args) {
		/*
		 * // TODO Auto-generated method stub String value ="Apple123"; for(char str:
		 * value.toCharArray()) MyConsole.print(str);
		 */
		
		/*
		 * List<Book> myBooks = new ArrayList<Book>(); myBooks.add(new Book(123,
		 * "Pro Java", 560)); myBooks.add(new Book(123, "Pro Java", 560));
		 * myBooks.add(new Book(123, "Pro Java", 560)); myBooks.add(new Book(123,
		 * "Pro Java", 560)); //The object of the List Interface is used within a
		 * foreach statement... for(Book bk : myBooks) MyConsole.print(bk);
		 */
		
		try {
			BookStore lib = new BookStore();
			lib.addBook(new Book(123, "Pro java", 600));
			lib.addBook(new Book(124, "Inside COM", 500));
			lib.addBook(new Book(125, "Professional Java", 670));
			lib.addBook(new Book(126, "Learning Springs", 350));
			//Now the object is usable in a foreach statement, this class is now a Collection class....
			/*
			 * for(Book bk : lib) MyConsole.print(bk);
			 */
			Iterator<Book> it = lib.iterator();
			while(it.hasNext())
				MyConsole.print(it.next());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
