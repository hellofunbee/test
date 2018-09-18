package com.jingu.IOT;

import com.jingu.IOT.dao.IPCDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.beans.factory.annotation.Autowired;



public class AppTest 
    extends TestCase
{

    @Autowired
    IPCDao ipcService;


    public AppTest(){}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


}
