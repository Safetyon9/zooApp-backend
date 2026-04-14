package com.betacom.utenti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.controllers.commerce.UtentiController;
import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.RegisterReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.LoginDTO;
import com.betacom.dto.outputs.RegisterDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.UtentiResp;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiControllerTest {
	
	@Autowired
	private UtentiController utentiC;
	
	@Autowired
	private IMessaggiServices msgS;
	
	@Autowired 
	private IUtentiRepository utR;
	
	@Autowired 
	private IClientiRepository clR;
	
	@Test
	@Order(1)
	public void getUtenti() {
		log.debug("Test getUtenti");
		ResponseEntity<?> resp = utentiC.findByUserName("Test1");
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		UtentiDTO ute = (UtentiDTO) resp.getBody();
		Assertions.assertThat(ute.getEmail()).isEqualTo("test@email.com");
	
	}
	
	@Test
	@Order(2)
	public void getUtentiError() {
		log.debug("Test getUtenti");
		ResponseEntity<?> resp = utentiC.findByUserName("Test1000");
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	
	}
	
	@Test
	@Order(3)	
	public void createUtenti() {

		log.debug("Create Utenti");
		UtentiReq req = new UtentiReq();
		req.setUsername("Test3");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("a.bella@gmail.com");
		
		ResponseEntity<?> resp = utentiC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
		
	}
	@Test
	@Order(12)	
	public void createUtentiErr() {

		log.debug("Create Utenti");
		UtentiReq req = new UtentiReq();
		req.setUsername("Test3");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("mailsbagliata");
		
		ResponseEntity<?> resp = utentiC.create(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	
	@Test
	@Order(4)	
	public void updateUtenti() {
		log.debug("******* Update utenti  *******");
		
		Utenti ut = TestDataFactory.creaUtenteValido(utR,"1");
		
		
		UtentiReq req = new UtentiReq();
		req.setUsername(ut.getUserName());
		req.setPwd("labrutta");
		
		
		ResponseEntity<Resp> resp = utentiC.update(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
			
	}
	
	@Test
	@Order(1)	
	public void updateUtentiErr() {
		log.debug("******* Update utenti error *******");
		
		UtentiReq req = new UtentiReq();
		req.setUsername("Test99");
		req.setPwd("LaBella");
		req.setRole("User");
		req.setEmail("test@gmail.com");
		
		ResponseEntity<Resp> resp = utentiC.update(req);
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
			
	}
	
	@Test
	@Order(5)	
	public void deleteUtenti() {
		log.debug("******* delete utenti  *******");
		
		Utenti ut = TestDataFactory.creaUtenteValido(utR,"2z");
		
		ResponseEntity<Resp> resp = utentiC.delete(ut.getUserName());
		
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
		
	}
	
	@Test
	@Order(5)	
	public void deleteUtentiErr() {
		log.debug("******* delete utenti error *******");
		
		
		ResponseEntity<Resp> resp = utentiC.delete("Test400");
	
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
		
	}
	
	@Test
	@Order(8)	
	public void list() {
		log.debug("Test list Utenti");
		
		ResponseEntity<?> resp = utentiC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Object body = resp.getBody();
		
		List<UtentiDTO> lS = (List<UtentiDTO>) body;
		
		Assertions.assertThat(lS.size()).isGreaterThan(0);
	//	Assertions.assertThat(lS.get(0).getCognome()).isEqualTo("Rossi");
		lS.forEach(s -> log.debug(s.toString()));
		// updateSocio();
	}
	
	
	@Test
	@Order(9)	
	public void login() {
		log.debug("Test list Utenti");
		
		LoginReq req = new LoginReq();
		req.setUsername("Test3");
		req.setPwd("LaBella");
		
		ResponseEntity<?> resp = utentiC.login(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		LoginDTO login = (LoginDTO) resp.getBody();
		Assertions.assertThat(login.getUsername()).isEqualTo("Test3");

	}
	
	@Test
	@Order(10)
	public void loginError() {
	    LoginReq req = new LoginReq();
	    req.setUsername("InvalidUser"); 
	    req.setPwd("wrongpwd");
	    
	    ResponseEntity<Object> resp = utentiC.login(req); 
	    assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	    
	    Object body = resp.getBody();
	    Assertions.assertThat(body).isInstanceOf(Resp.class);  
	    Resp errorResp = (Resp) body;
	}
	
	@Test
	@Order(11)
	public void register() {
		log.debug("Test register OK");

        UtentiReq u = new UtentiReq();
        u.setUsername("TEST1747");
        u.setEmail("ciao@gmail.com");
        u.setPwd("12341414");
        u.setRole("USER");

        ClientiReq c = new ClientiReq();
        c.setNome("asdadad");
        c.setCognome("Register1234412");
        c.setIndirizzo("asdadasdad");
        c.setCap("00124");
        c.setComune("Ciao");
        c.setTelefono("123456789");
        c.setProvincia("Roma");
	    
	    RegisterReq req = new RegisterReq(u, c);
	    
	    ResponseEntity<?> resp = utentiC.register(req);
	    assertEquals(HttpStatus.OK, resp.getStatusCode()); 
	    RegisterDTO body = (RegisterDTO) resp.getBody();
		Assertions.assertThat(body.getEmail()).isEqualTo("ciao@gmail.com");

	}
	
	@Test
	@Order(12)
	public void findAllByUserName() {
		log.debug("Test getUtenti");
		
		
		
		Utenti u = TestDataFactory.creaUtenteValido(utR,"104");
		
		Clienti c = new Clienti();
		
		c.setNome("Mario");
        c.setCognome("Rossi");
        c.setIndirizzo("Via Roma 1");
        c.setUtente(u);
        c.setComune("Roma");
        c.setCap("00100");
        c.setTelefono("3331234567");
        c.setProvinca("RM");
		
		ResponseEntity<?> resp = utentiC.findAllByUserName("testuser104");
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		UtentiResp ute = (UtentiResp) resp.getBody();
		Assertions.assertThat(ute.getEmail()).isEqualTo("test104@mail.com");
	
	}
	@Test
	@Order(13)	
	public void updateAllUtenti() {
		
		UtentiReq u = new UtentiReq();
		u.setUsername("TEST1747");
        u.setEmail("ciao@gmail.com");
        u.setPwd("12341414");
        u.setRole("USER");
		
        ClientiReq c = new ClientiReq();
        c.setNome("Mario");
        c.setCognome("Rossi");
        c.setIndirizzo("Via Roma 1");
        c.setComune("Roma");
        c.setCap("00100");
        c.setTelefono("3331234567");
        
        
		RegisterReq req = new RegisterReq(u,c);
		req.setUtente(u);
		
		
		ResponseEntity<Resp> resp = utentiC.Allupdate(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
	}
	
	@Test
	@Order(14)	
	public void updateAllUtentiErr() {
		
		UtentiReq u = new UtentiReq();
		u.setUsername("TEST1747");
        u.setEmail("ciao@gmail.com");
        u.setPwd("12341414");
        u.setRole("USER");
        
		RegisterReq req = new RegisterReq(u,null);
		req.setUtente(u);
		
		ResponseEntity<Resp> resp = utentiC.Allupdate(req);
		
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	@Test
	@Order(15)	
	public void logout() {
		log.debug("Test logout Utenti");
		
		Utenti u = TestDataFactory.creaUtenteValido(utR,"103");
        
		ResponseEntity<?> resp = utentiC.logout(u.getUserName());
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertThat(u.getIsActive()).isFalse();
	}
	@Test
	@Order(16)	
	public void changePwd() {
		log.debug("******* change pwd utenti  *******");
		
		Utenti ut = TestDataFactory.creaUtenteValido(utR,"1");
		
		
		UtentiReq req = new UtentiReq();
		req.setUsername(ut.getUserName());
		
		
		req.setOldPwd(ut.getPwd());
		
		req.setNewPwd(ut.getPwd()+"1");
		req.setPwd(ut.getPwd()+"1");
		
		ResponseEntity<Resp> resp = utentiC.changePwd(req);
	
		
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = (Resp)resp.getBody();
		log.debug(r.getMsg());
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
			
	}
	
	
	
	
	
	
	
	
	
	
}