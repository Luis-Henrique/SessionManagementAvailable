package br.com.luish.management.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class CPFUtil {

	public static boolean isValid(String cpf) {
		

		RestTemplate restTemplate = new RestTemplateBuilder()
	            .rootUri(ConstantesUtil.URL_CPF + cpf).build();

	  String clientRest= restTemplate.getForObject("/", String.class);
	 
	  if(clientRest.contains(ConstantesUtil.MSG_CPF_VALID))
		  return true;
	  
	  return false;
		
	}
	
	

}
