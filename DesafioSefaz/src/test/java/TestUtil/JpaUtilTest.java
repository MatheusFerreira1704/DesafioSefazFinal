package TestUtil;

import org.junit.Test;


import util.JpaUtil;

public class JpaUtilTest  {
	
	@Test
	public void connectionTest() {
		
		JpaUtil.getEntityManager();
		
	}		

}


