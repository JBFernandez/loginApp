package com.example.loginAPI;

import com.example.loginAPI.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; //importamos manualmente estos paquetes
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


// /api/        vamos a probar los endpoint
@SpringBootTest
@AutoConfigureMockMvc
class LoginApiApplicationTests {

	@Autowired // instancia nuestra aplicación para poder mandar las peticiones
	private MockMvc mockMvc;
//	@Test
//	void contextLoads() {
//	}

	@Test
	public void testGet() throws Exception { //Con este throws aquí, ya no tenemos que poner dentro try y catch
		this.mockMvc.perform(get("/api/login/users")).andDo(print()).andExpect(status().isOk());
	}//testGet

	@Test
	public void shoulReturn404Error() throws Exception {
		this.mockMvc.perform(get("/api/login/users/3").header("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXIuYXJlbmFzQGFuZXN0ZXNpby5jb20iLCJyb2xlIjoidXNlciIsImlhdCI6MTY0NjY5NDQ3MiwiZXhwIjoxNjQ2NzMwNDcyfQ._GhaKXYyrizFqRClfV3jH5byIE8da2k6uLqg4z2N2VQ")).andDo(print()).andExpect(status().is5xxServerError());
	}//shoulReturn404Error

	public static String asJSONString(final Object obj) { //creamos este método para utilizarlo debajo
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}//asJSONString

	@Test
	public void testPost() throws Exception {
		Users user = new Users();
		user.setEmail("hola@gmail.com");
		user.setClearPassword("prueba"); // creamos este setter nuevo porque el otro ya cifra la contraseña, cifraríamos x2
		this.mockMvc.perform(post("/api/login/users").contentType(MediaType.APPLICATION_JSON).content(asJSONString(user)))
				.andExpect(status().isOk());
	}//testPost

	@Test
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete("/api/login/users2")).andDo(print()).andExpect(status().isOk());
	}// testDelete

	@Test
	public void testPut() throws Exception {
		this.mockMvc.perform(put("/api/login/users/5?originalPassword=fernanda.&newPassword=fernanda1"))
				.andDo(print()).andExpect(status().isOk());
	} //testPut
}//class LoginApiApplicationTests
