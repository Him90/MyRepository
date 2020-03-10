package First;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.trivadis.books.client.BookDTO;

public class FirstTest {
	
	@Test
	public void addTest() {
		int one = 1;
		int test = 1;
		assertSame(one,test);
	}
	
	@Test
	public void addTitle() {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookTitle("TestTitle");
		String testTitleString = bookDTO.getBookTitle();
		
		assertSame(testTitleString, "TestTitle");
	}

}
