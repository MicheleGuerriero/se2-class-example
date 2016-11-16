package it.polimi.classexample.beans;

import org.junit.Test;

import it.polimi.classexample.utils.MockedUser;

import static org.mockito.Mockito.*;

import junit.framework.Assert;

public class AdditionBeanTest {
	
	@Test
	public void additionBeanTest() {
		AdditionBeanBasic addBean = new AdditionBeanBasic();
		
		
		MockedUser mockedUser = mock(MockedUser.class);
	    
	    when(mockedUser.getFirst()).thenReturn(43);
	    when(mockedUser.getSecond()).thenReturn(57);

		
		addBean.setI(mockedUser.getFirst());
		addBean.setJ(mockedUser.getSecond());
		addBean.add();

		Assert.assertEquals(new Integer(100), addBean.getK());
	}
	
	
}
