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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.betacom.controllers.commerce.UtentiController;
import com.betacom.dto.inputs.*;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.*;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.testutils.TestDataFactory;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtentiControllerTest {

    @MockitoBean
    private IMailServices mailServices;

    @Autowired
    private UtentiController controller;

    @Autowired
    private IUtentiRepository utR;

    // =========================
    // CREATE
    // =========================

    @Test
    @Order(1)
    public void create_ok() {

        UtentiReq req = new UtentiReq();
        req.setUsername("test_order");
        req.setPwd("123");
        req.setEmail("test_order@mail.com");
        req.setRole("USER");

        ResponseEntity<Resp> resp = controller.create(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Assertions.assertThat(resp.getBody().getMsg()).isNotNull();
    }

    @Test
    @Order(2)
    public void create_error_email() {

        UtentiReq req = new UtentiReq();
        req.setUsername("test_order2");
        req.setPwd("123");
        req.setEmail("email_sbagliata");
        req.setRole("USER");

        ResponseEntity<Resp> resp = controller.create(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // LOGIN
    // =========================

    @Test
    @Order(3)
    public void login_ok() {

        // se esiste già da DB/testdata
        LoginReq req = new LoginReq();
        req.setUsername("Test3");
        req.setPwd("LaBella");

        ResponseEntity<Object> resp = controller.login(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        Assertions.assertThat(resp.getBody())
                .isInstanceOf(LoginDTO.class);
    }

    @Test
    @Order(4)
    public void login_error() {

        LoginReq req = new LoginReq();
        req.setUsername("fake_user");
        req.setPwd("wrong");

        ResponseEntity<Object> resp = controller.login(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // LIST
    // =========================

    @Test
    @Order(5)
    public void list_ok() {

        ResponseEntity<Object> resp = controller.list();

        assertEquals(HttpStatus.OK, resp.getStatusCode());

        @SuppressWarnings("unchecked")
        List<UtentiDTO> list = (List<UtentiDTO>) resp.getBody();

        Assertions.assertThat(list).isNotNull();
    }

    // =========================
    // UPDATE
    // =========================

    @Test
    @Order(6)
    public void update_ok() {

        Utenti ut = TestDataFactory.creaUtenteValido(utR, "900");

        UtentiReq req = new UtentiReq();
        req.setUsername(ut.getUserName());
        req.setPwd("nuova_pwd");

        ResponseEntity<Resp> resp = controller.update(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    @Order(7)
    public void update_error_user_not_found() {

        UtentiReq req = new UtentiReq();
        req.setUsername("non_esiste");
        req.setPwd("123");

        ResponseEntity<Resp> resp = controller.update(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // DELETE
    // =========================

    @Test
    @Order(8)
    public void delete_ok() {

        Utenti ut = TestDataFactory.creaUtenteValido(utR, "901");

        ResponseEntity<Resp> resp = controller.delete(ut.getUserName());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    @Order(9)
    public void delete_error() {

        ResponseEntity<Resp> resp =
                controller.delete("utente_inesistente");

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // REGISTER
    // =========================

    @Test
    @Order(10)
    public void register_ok() {

        UtentiReq u = new UtentiReq();
        u.setUsername("reg_user");
        u.setEmail("reg@mail.com");
        u.setPwd("123");
        u.setRole("USER");

        ClientiReq c = new ClientiReq();
        c.setNome("Mario");
        c.setCognome("Rossi");
        c.setIndirizzo("Via Roma");
        c.setCap("00100");
        c.setComune("Roma");
        c.setTelefono("123");
        c.setProvincia("RM");

        RegisterReq req = new RegisterReq(u, c);

        ResponseEntity<Object> resp = controller.register(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());

        Assertions.assertThat(resp.getBody())
                .isInstanceOf(RegisterDTO.class);
    }

    // =========================
    // CHANGE PWD
    // =========================

    @Test
    @Order(11)
    public void changePwd_ok() {

        Utenti ut = TestDataFactory.creaUtenteValido(utR, "902");

        UtentiReq req = new UtentiReq();
        req.setUsername(ut.getUserName());
        req.setOldPwd(ut.getPwd());
        req.setNewPwd("nuova123");

        ResponseEntity<Resp> resp = controller.changePwd(req);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    @Order(12)
    public void changePwd_error() {

        UtentiReq req = new UtentiReq();
        req.setUsername("fake");
        req.setOldPwd("a");
        req.setNewPwd("b");

        ResponseEntity<Resp> resp = controller.changePwd(req);

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // PASSWORD DIMENTICATA
    // =========================

    @Test
    @Order(13)
    public void forgot_password() {

        ResponseEntity<Resp> resp =
                controller.passwordDimenticata("test@mail.com");

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    // =========================
    // EMAIL VALIDATE
    // =========================

    @Test
    @Order(14)
    public void validation_error() {

        ResponseEntity<Resp> resp =
                controller.emailValidate("token_fake");

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    // =========================
    // SEARCH
    // =========================

    @Test
    @Order(15)
    public void search_ok() {

        UtentiReq req = new UtentiReq();
        req.setUsername("Test3");

        var resp = controller.search(req);

        Assertions.assertThat(resp).isNotNull();
    }

    // =========================
    // LOGOUT
    // =========================

    @Test
    @Order(16)
    public void logout_ok() {

        Utenti ut = TestDataFactory.creaUtenteValido(utR, "903");

        ResponseEntity<Resp> resp =
                controller.logout(ut.getUserName());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Test
    @Order(17)
    public void logout_error() {

        ResponseEntity<Resp> resp =
                controller.logout("utente_fake");

        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }
}