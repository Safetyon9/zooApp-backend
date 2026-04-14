package com.betacom.categorie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.betacom.config.TestMailConfig;
import com.betacom.controllers.commerce.items.CategorieController;
import com.betacom.dto.inputs.commerce.items.CategorieReq;
import com.betacom.dto.outputs.commerce.items.CategorieDTO;
import com.betacom.persistence.entity.commerce.items.Categorie;
import com.betacom.persistence.repository.commerce.items.ICategorieRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

import lombok.extern.slf4j.Slf4j;


@Import(TestMailConfig.class)
@ActiveProfiles("test")
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategorieControllerTest {
	
	@Autowired
	private CategorieController catC;
	
	@Autowired
	private IMessaggiServices msgS;
	
	@Autowired
	private ICategorieRepository catR;
	
	@Test
	@Order(1)
	public void create() {
		log.debug("Create categorie");
		CategorieReq req = new CategorieReq();
		req.setNome("Test");

		ResponseEntity<?> resp = catC.create(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		
		Resp r = (Resp) resp.getBody();
		
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_created"));
	}
	
	@Test
	@Order(2)
	public void createErr() {
		log.debug("Create categorie err");
		CategorieReq req = new CategorieReq();
		req.setId(1);
		req.setNome("TestError");

		ResponseEntity<?> resp = catC.create(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	
	@Test
	@Order(3)
	public void findById() {
		log.debug("Test findById");
		Categorie cat = TestDataFactory.creaCategoriaValida(catR,"1");
		
		ResponseEntity<?> resp = catC.findById(cat.getId());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
		CategorieDTO prod = (CategorieDTO) resp.getBody();
		Assertions.assertThat(prod.getNome()).isEqualTo("Categoria1");
	}
	
	@Test
	@Order(4)
	public void findByIdErr() {
		log.debug("Test findById");
		ResponseEntity<?> resp = catC.findById(100);
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	@Test
	@Order(5)
	public void update() {
		log.debug("Update categorie");
		Categorie cat = TestDataFactory.creaCategoriaValida(catR,"2");
		
		CategorieReq req = new CategorieReq();
		req.setId(cat.getId());
		req.setNome("Update");

		ResponseEntity<Resp> resp = catC.update(req);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = resp.getBody();
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_updated"));
	}
	
	@Test
	@Order(6)
	public void updateErr() {
		log.debug("Update categorie");
		CategorieReq req = new CategorieReq();
		req.setId(99);
		req.setNome("Update");

		ResponseEntity<Resp> resp = catC.update(req);
		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
	
	@Test
	@Order(7)
	public void list() {
		log.debug("Test list categorie");
		ResponseEntity<?> resp = catC.list();
		assertEquals(HttpStatus.OK, resp.getStatusCode());
		List<CategorieDTO> listP = (List<CategorieDTO>) resp.getBody();
		Assertions.assertThat(listP).isNotEmpty();
		listP.forEach(p -> log.debug(p.toString()));
	}
	
	@Test
	@Order(8)
	public void delete() {
		log.debug("Delete categorie");
		Categorie cat = TestDataFactory.creaCategoriaValida(catR,"5");
		
		ResponseEntity<Resp> resp = catC.delete(cat.getId());

		assertEquals(HttpStatus.OK, resp.getStatusCode());
		Resp r = resp.getBody();
		
		Assertions.assertThat(r.getMsg())
        .isEqualTo(msgS.get("rest_deleted"));
	}
	
	@Test
	@Order(9)
	public void deleteErr() {
		log.debug("Delete categorie");
		ResponseEntity<Resp> resp = catC.delete(100);

		assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}
}
