package bruchalex.remna_shop.user.rest;

import bruchalex.remna_shop.user.adapter.in.web.AuthController;
import bruchalex.remna_shop.user.adapter.in.web.dto.RegisterUserRequest;
import bruchalex.remna_shop.user.application.LoginUserUseCase;
import bruchalex.remna_shop.user.application.RegisterUserUseCase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockitoBean private RegisterUserUseCase useCase;
    @MockitoBean private LoginUserUseCase loginUseCase;

    @Test
    void register_returns400_whenEmailInvalid() throws Exception {
        var request = new RegisterUserRequest("notValidEmail", "password123", "password123");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyNoInteractions(useCase);
    }
}
