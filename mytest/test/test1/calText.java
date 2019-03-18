package test1;

import static org.junit.Assert.*;

import org.junit.Test;

public class calText {
    cal cal1=new cal();
    
    
	@Test
	public void test() {
		assertEquals(false,cal1.Triangle(100));
	}
	
    
}
