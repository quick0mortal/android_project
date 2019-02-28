package test;

import String.String;
import data.Project;
import data.User;
import junit.framework.TestCase;

/**
 * Created by quick on 2017/6/23.
 */
public class TestUserID extends TestCase {
    public void testUser(){
        User user = new User(2);
        int result = user.getUserID();
        assertEquals(2,result,0);
    }
}


