/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Comment;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TJ
 */
public class CommentDAOTest {
    
    public CommentDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllComments method, of class CommentDAO.
     */
    @Test
    public void testGetAllComments() {
        System.out.println("getAllComments");
        CommentDAO instance = null;
        List<Comment> expResult = null;
        List<Comment> result = instance.getAllComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentsOfUser method, of class CommentDAO.
     */
    @Test
    public void testGetCommentsOfUser() {
        System.out.println("getCommentsOfUser");
        int userId = 0;
        CommentDAO instance = null;
        List<Comment> expResult = null;
        List<Comment> result = instance.getCommentsOfUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommentsOfPost method, of class CommentDAO.
     */
    @Test
    public void testGetCommentsOfPost() {
        System.out.println("getCommentsOfPost");
        int postId = 0;
        CommentDAO instance = null;
        List<Comment> expResult = null;
        List<Comment> result = instance.getCommentsOfPost(postId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeCommment method, of class CommentDAO.
     */
    @Test
    public void testMakeCommment() {
        System.out.println("makeCommment");
        int userId = 0;
        int postId = 0;
        String content = "";
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.makeCommment(userId, postId, content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteComment method, of class CommentDAO.
     */
    @Test
    public void testDeleteComment() {
        System.out.println("deleteComment");
        int commentID = 0;
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.deleteComment(commentID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateComment method, of class CommentDAO.
     */
    @Test
    public void testUpdateComment() {
        System.out.println("updateComment");
        int commentID = 0;
        String content = "";
        CommentDAO instance = null;
        boolean expResult = false;
        boolean result = instance.updateComment(commentID, content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
