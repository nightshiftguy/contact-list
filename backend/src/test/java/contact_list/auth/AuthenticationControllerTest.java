package contact_list.auth;

import contact_list.security.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean AuthenticationService authenticationService;

  @Test
  @DisplayName("Register can be accessed")
  void testAuthorizationController_whenValidUserIsPassed_registerIt() throws Exception {
    // Arrange
    RegistrationRequest registrationRequest = new RegistrationRequest("test@mail", "password123");

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(registrationRequest));

    // Act
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    // Assert
    Assertions.assertEquals(204, mvcResult.getResponse().getStatus());
  }
}
