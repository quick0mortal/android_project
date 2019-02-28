package test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Created by quick on 2017/6/23.
 */
public class TestRun extends TestSuite{
    public static Test suite(){
        TestSuite suite = new TestSuite("TestSuite Test");
        suite.addTestSuite(TestUserID.class);
        return suite;
    }
    public static void main(String args[]){
        TestRunner.run(suite());
    }
}



