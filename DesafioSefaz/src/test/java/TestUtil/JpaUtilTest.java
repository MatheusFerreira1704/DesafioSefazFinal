package TestUtil;

import org.junit.Test;

import util1.JpaUtil;

public class JpaUtilTest  {
	
	@Test
	public void connectionTest() {
		
		JpaUtil.getEntityManager();
		
	}		

}


