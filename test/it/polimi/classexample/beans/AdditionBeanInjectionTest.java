package it.polimi.classexample.beans;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.polimi.classexample.entities.IntPair;
import junit.framework.Assert;

@RunWith(Arquillian.class)
public class AdditionBeanInjectionTest {

	@EJB
	private AdditionBean addBean;
	
	private static final String container = "glassfish-remote";

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClasses(AdditionBean.class, IntPair.class)
				.addAsResource("resources-" + container + "/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Test
	public void additionBeanInjectionTest() {
		addBean.setI(Integer.parseInt("3"));
		addBean.setJ(Integer.parseInt("4"));
		addBean.add();
		Assert.assertEquals(new Integer(3), addBean.getI());
		Assert.assertEquals(new Integer(4), addBean.getJ());
		Assert.assertEquals(new Integer(7), addBean.getK());
	}
	
}
