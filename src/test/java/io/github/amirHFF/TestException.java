package io.github.amirHFF;

import io.github.amirHFF.errorCode.ErrorCodeBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TestException {

	@Test
	public void testBuild(){
		ErrorCodeBuilder.moduleName = "amir";
		Assert.assertEquals("amir-1234" ,ErrorCodeBuilder.build("1234"));
	}
	@Test
	public void testBuild2(){
		ErrorCodeBuilder.moduleName = "amir";
		Assert.assertEquals("ami331r-1234" ,ErrorCodeBuilder.build("ami331r-1234"));
	}

}
