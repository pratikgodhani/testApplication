package security;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.test.model.UserDetail;
import com.test.security.CustomAuthenticationProvider;
import com.test.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class CustomAuthenticationProviderSpec {
	
	@InjectMocks
	CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
	
	@Mock
	Authentication authentication;
	
	@Mock Model model;
	@Mock LoginService iLoginService;
	@Mock UserDetail userDetail;
	@Mock Environment env;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenUserIsValid() throws Exception {
		
		when(authentication.getName()).thenReturn("userName");
		when(authentication.getCredentials()).thenReturn("password");
		when(iLoginService.isValidUser("userName", "password")).thenReturn(true);
		//Then
		assertThat(authenticationProvider.authenticate(authentication)).isNotNull();		
	}
	
	@Test
	public void givenUserIsNotValid() throws Exception {
		
		when(authentication.getName()).thenReturn("userName");
		when(authentication.getCredentials()).thenReturn("password");
		when(iLoginService.isValidUser("userName", "password")).thenReturn(false);
		//Then
		assertThat(authenticationProvider.authenticate(authentication)).isNull();		
	}

}
