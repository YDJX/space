package jibxbind;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.management.MXBean;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mythink.netty.bean.Account;
import org.mythink.netty.bean.Birthday;

public class JibxTest {

	private IBindingFactory factory = null;
	private StringWriter strWriter =null;
	private StringReader strReader =null;
	
	private Account bean=null;
	
	@Before
	public void init(){
		bean = new Account();
		bean.setAddress("beijing");
		bean.setEmail("email@1653.com");
		bean.setId(0);
		bean.setName("abc");
		bean.setBirthday(new Birthday("xxxyear"));
		
		try {
			factory = BindingDirectory.getFactory(Account.class);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void destory(){
		bean = null;
		try {
			if(strWriter!=null){
				strWriter.close();
			}
			
			if(strReader!=null){
				strReader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void bean2Xml(){
		try {
			strWriter = new StringWriter();
			IMarshallingContext ctx = factory.createMarshallingContext();
			ctx.setIndent(2);
			ctx.marshalDocument(bean,"UTF-8",null,strWriter);
			fail(strWriter);
			strReader = new StringReader(strWriter.toString());
			IUnmarshallingContext umctx = factory.createUnmarshallingContext();
			Account ac = (Account)umctx.unmarshalDocument(strReader, null);
			fail(ac);
		} catch (JiBXException e) {
			e.printStackTrace();
		}
	}
	
	private void fail(Object a){
		System.out.println(a);
	}
	
	public static void main(String[] args){
		
	}
}
