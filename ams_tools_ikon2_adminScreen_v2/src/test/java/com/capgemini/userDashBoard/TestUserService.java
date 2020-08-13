package com.capgemini.userDashBoard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.capgemini.userDashBoard.entities.MyUser;
import com.capgemini.userDashBoard.repository.UserJpaRepository;
import com.capgemini.userDashBoard.service.UserService;

//Testing for service layer

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	@InjectMocks
	UserService serviceMockObj;
	
	@Mock
	UserJpaRepository repoMockObj;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void shouldAddUser() {
		MyUser mockU1=new MyUser(1L, "testEmp", "test1", "test2", "test3", "test3", "test4");
		serviceMockObj.addUser(mockU1);	
		verify(repoMockObj, times(1)).save(mockU1);
	}
	
	@Test
	public void shoudRertunAllUsers(){
		//first need to add users into list(this list will act as database for testing)
		//1
		List<MyUser> lst= new ArrayList<>();
		lst.add(new MyUser(1L, "test1","test2","test3","test4","test5", "test6"));
		lst.add(new MyUser(2L, "test2","test2","test3","test4","test5", "test6"));
		lst.add(new MyUser(3L, "test3","test2","test3","test4","test5", "test6"));
		
		/*here we are diverting flow from actual repository to our mock repository
		 * that means when anyone call findAll method from repository then it will take list from
		 * lst instead of actual database . This is the step one where we are created database and diverted call to our database*/
		
		when(repoMockObj.findAll()).thenReturn(lst);
		
		/*Now here we are giving call to service's method which will internally call to repository method. But as above we redirected call from actual repo to above mock repo
		 *So it will return above list of users. 
		 * */
		List<MyUser> userList = serviceMockObj.findAll();
         
		/*This is the actual test case where we are comparing size from the database*/
        assertEquals(3, userList.size());
        verify(repoMockObj, times(1)).findAll();
	}
	
	@Test
	public void shouldDeleteUser() {
		List<MyUser> user=new ArrayList<>();
		user.add(new MyUser(100L, "userName", "shortname", "email@capgemin.com", "roleCategory", "applicationName", "assignmentGroup"));
	
		serviceMockObj.deleteById(100L);
		verify(repoMockObj, times(1)).deleteById(100L);
	}
	
//	@Test
//	public void shouldThrowExceptionUserNotFound() {
//		Long userId=100L;
//		List<MyUser> user=new ArrayList<>();
//		user.add(new MyUser(100L, "userName", "shortname", "email@capgemin.com", "roleCategory", "applicationName", "assignmentGroup"));
//		user.add(new MyUser(200L, "userName", "shortname", "email@capgemin.com", "roleCategory", "applicationName", "assignmentGroup"));
//
//		serviceMockObj.findById(userId);
//		
//		assertEquals(1, repoMockObj.findById(100L));
//		
//	}
}

