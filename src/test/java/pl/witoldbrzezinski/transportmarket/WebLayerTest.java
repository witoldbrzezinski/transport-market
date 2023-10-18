package pl.witoldbrzezinski.transportmarket;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import pl.witoldbrzezinski.transportmarket.load.LoadDTOResponse;
import pl.witoldbrzezinski.transportmarket.customer.CustomerServiceImpl;
import pl.witoldbrzezinski.transportmarket.load.LoadServiceImpl;
import pl.witoldbrzezinski.transportmarket.security.UserServiceImpl;

@WebMvcTest
@AutoConfigureTestDatabase
public class WebLayerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean CustomerServiceImpl customerService;
  @MockBean LoadServiceImpl loadService;
  @MockBean UserServiceImpl userService;

  @Test
  @WithMockUser
  public void basicTemplateShouldContainAppName() throws Exception {
    Page<LoadDTOResponse> loadPage = Mockito.mock(Page.class);
    when(loadService.findPaginated(Mockito.any())).thenReturn(loadPage);
    this.mockMvc
        .perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Transport Market")));
  }
}
