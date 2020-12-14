package br.com.testeapinovo;
import org.json.simple.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.CoreMatchers;
public class RegistrarUsuarioComSucesso {
	@DataProvider(name="dadosParaRegistro")
	public Object [][] dadosParaRegistro(){
		
		Object [][] dado = new Object[2][2];
		dado[0][0] = "eve.holt@reqres.in";
		dado[0][1] = "pistol";
		
		dado[1][0] = "eve.holt@reqres.in";//eu iria inserir outro user mas a api só aceita usuários específicos
		dado[1][1] = "pistol";    			// "Note: Only defined users succeed registration"
		
		return dado;
	}
	//
	@Test(dataProvider = "dadosParaRegistro")
	void Registro_com_sucesso(String email, String password) {

		String url = "https://reqres.in/api/register";
		
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("email", email); 
		requestParams.put("password", password);

		given().
			header("Content-Type","application/json").
			body(requestParams.toJSONString()).
			
		when().
			post(url).
		then().
			statusCode(200).
		and().
			body(CoreMatchers.containsString("token"));
	}
}
