package service;

import dao.UserDao;
import entity.User;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    public UserService userService;
    public UserDao userDao;
    @BeforeEach
    public void init() {
        userService = new UserService();
        userDao = EasyMock.createMock(UserDao.class);
    }

    @Test
    public void loginVerifyTest1() {
        userService.setUserDao(userDao);
        String username = "123";
        String password = "123";
        expect(userDao.verifyUsername(username)).andReturn(Boolean.TRUE);
        expect(userDao.verifyPassword(username,password)).andReturn(Boolean.TRUE);
        replay(userDao);
        assertEquals(true, userService.loginVerify(username,password));
        verify(userDao);
    }

    @Test
    public void loginVerifyTest2() {
        userService.setUserDao(userDao);
        String username = "123";
        String password = "123";
        expect(userDao.verifyUsername(username)).andReturn(Boolean.TRUE);
        expect(userDao.verifyPassword(username,password)).andReturn(Boolean.FALSE);
        replay(userDao);
        assertEquals(false, userService.loginVerify(username,password));
        verify(userDao);
    }

    @Test
    public void loginVerifyTest3() {
        userService.setUserDao(userDao);
        String username = "123";
        String password = "123";
        expect(userDao.verifyUsername(username)).andReturn(Boolean.FALSE);
//        expect(userDao.verifyPassword(username,password)).andReturn(Boolean.TRUE);
        replay(userDao);
        assertEquals(false, userService.loginVerify(username,password));
        verify(userDao);
    }

    @Test
    public void userRegisterTest1() {
        userService.setUserDao(userDao);
        String username = "123";
        String password = "123";
        expect(userDao.addUser(EasyMock.<User>anyObject())).andReturn(Boolean.TRUE);
        replay(userDao);
        assertEquals(true, userService.userRegister(username,password));
        verify(userDao);
    }

    @Test
    public void userRegisterTest2() {
        userService.setUserDao(userDao);
        String username = "123";
        String password = "123";
        expect(userDao.addUser(EasyMock.<User>anyObject())).andReturn(Boolean.FALSE);
        replay(userDao);
        assertEquals(false, userService.userRegister(username,password));
        verify(userDao);
    }
}