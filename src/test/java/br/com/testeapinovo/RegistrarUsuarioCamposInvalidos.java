package br.com.testeapinovo;
import org.json.simple.JSONObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.hamcrest.CoreMatchers;

public class RegistrarUsuarioCamposInvalidos {
	@DataProvider(name="dadosParaRegistro2")
	public Object [][] dadosParaRegistro2(){
		
		Object [][] dado = new Object[3][2];
		dado[0][0] = "";
		dado[0][1] = "";
		
		dado[1][0] = "eve.holt@reqres.in";
		dado[1][1] = "";    			
		
		dado[2][0] = "";
		dado[2][1] = "1234567";
		return dado;
	}


@Test(dataProvider = "dadosParaRegistro2")
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
			statusCode(400);
			

	}
}
