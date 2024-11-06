package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BaseTestsTest
        extends TestCase
{
    public BaseTestsTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( TestLogin.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
