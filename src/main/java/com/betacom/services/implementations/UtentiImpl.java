package com.betacom.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.MailReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.LoginDTO;
import com.betacom.dto.outputs.RegisterDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.UtentiResp;
import com.betacom.enums.Roles;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Carrelli;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.ICarrelliRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.persistence.specification.UtentiSpecification;
import com.betacom.services.interfaces.IMailServices;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.IUtentiServices;
import com.betacom.utilities.Mapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.core.io.ClassPathResource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtentiImpl implements IUtentiServices {

	@Value("${mail.validation}")
	private String validationURL;
	
	@Value("${mail.resetPassword}")
	private String resetPasswordURL;
	
	private final IUtentiRepository repoU;
    private final IClientiRepository repoC;
    private final ICarrelliRepository repoCa;
    private final IMailServices mailS;
    

    private final IMessaggiServices msgS;

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void create (UtentiReq Ureq){
        log.debug("create {}", Ureq);

        if (repoU.findByUserName(Ureq.getUsername()).isPresent()) {
            throw new ZooException(msgS.get("user_exists"));
        }
        
        if (repoU.findByEmail(Ureq.getEmail()).isPresent()) {
            throw new ZooException(msgS.get("email_exists"));
        }
        
        if (!Ureq.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        	 throw new ZooException(msgS.get("email_invalid"));
	    }

        Utenti u = new Utenti();
        u.setUserName(Ureq.getUsername());
        u.setEmail(Ureq.getEmail());
        u.setPwd(Ureq.getPwd());
        u.setRole(Roles.valueOf(Ureq.getRole().toUpperCase()));
        u.setIsValidate(true);
        u.setIsActive(false);
        

        repoU.save(u);
    }
    
    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(UtentiReq req) throws ZooException {
    	log.debug("update {}", req);

        Utenti u = repoU.findByUserName(req.getUsername())
                .orElseThrow(() -> new ZooException(msgS.get("usr_id_ntfnd")));
        
        if(req.getUsername() != null)
        	u.setUserName(req.getUsername());
        
        if(req.getEmail() != null)
        	u.setEmail(req.getEmail());
        
        if(req.getRole() != null) {
        	u.setRole(Roles.valueOf(req.getRole().toUpperCase()));
        }
    }
    

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void Allupdate(UtentiReq Ureq, ClientiReq Creq) throws ZooException {
        log.debug("Allupdate {} {}", Ureq, Creq);

        Utenti u = repoU.findByUserName(Ureq.getUsername())
                .orElseThrow(() -> new ZooException(msgS.get("usr_id_ntfnd")));
        
        Clienti c = repoC.findById(u.getCliente().getId())
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));
        
        if(Ureq.getUsername() != null)
        	u.setUserName(Ureq.getUsername());
        
        if(Ureq.getEmail() != null)
        	u.setEmail(Ureq.getEmail());
        
        if(Creq.getNome() != null)
        	c.setNome(Creq.getNome());
        
        if(Creq.getCognome() != null)
        	c.setCognome(Creq.getCognome());
        
        if(Creq.getIndirizzo() != null)
        	c.setIndirizzo(Creq.getIndirizzo());
        
        if(Creq.getComune() != null)
        	c.setComune(Creq.getComune());
        
        if(Creq.getCap() != null)
        	c.setCap(Creq.getCap());
        
        if(Creq.getTelefono() != null)
        	c.setTelefono(Creq.getTelefono());
        
        if(Creq.getProvincia() != null)
        	c.setProvinca(Creq.getProvincia());
        
        if(Ureq.getRole() != null)
        	u.setRole(Roles.valueOf(Ureq.getRole().toUpperCase()));

        repoU.save(u);
        repoC.save(c);
    }
    
    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(String username) throws ZooException {
        log.debug("delete {}", username);

        Utenti u = repoU.findByUserName(username)
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));
        repoU.delete(u);
    }

    @Override
    public List<UtentiDTO> list() {
        log.debug("list utenti");

        return repoU.findAll().stream()
                .map(u -> Mapper.buildUtentiDTO(u))
                .toList();
    }

    @Override
    public UtentiDTO getByUserName(String userName) throws ZooException {
        log.debug("getByUserName {}", userName);

        Utenti u = repoU.findByUserName(userName)
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

        return Mapper.buildUtentiDTO(u);
    }


	@Override
	public UtentiResp getAllByUser(String userName) throws ZooException {
		log.debug("getAllByUserName {}", userName);

        Utenti u = repoU.findByUserName(userName)
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));
        
        if (u.getCliente() == null || u.getCliente().getId() == null) {
        	return Mapper.buildUtentiResp(u, null);
        }
        
        Clienti c = repoC.findById(u.getCliente().getId())
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

        return Mapper.buildUtentiResp(u, c);
	}
    
    @Override
    public LoginDTO login(LoginReq req) throws ZooException {
        log.debug("login {}", req);
        Utenti utente = repoU.findByUserName(req.getUsername())
                .orElseThrow(() -> new ZooException(msgS.get("login_invalid")));
        
        utente.setIsActive(true);
        repoU.save(utente);
        log.debug("UTENTE ONLINE/OFFLINE: "+utente.getIsActive());

        if(!utente.getPwd().equals(req.getPwd()))
            throw new ZooException(msgS.get("login_invalid"));
        return LoginDTO.builder()
                .username(utente.getUserName())
                .ruolo(utente.getRole().toString())
                .build();
    }

    @Override
    @Transactional(rollbackFor = ZooException.class)
    public void logout(String userName) throws ZooException {
        Utenti utente = repoU.findByUserName(userName)
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

        utente.setIsActive(false);
        repoU.save(utente);
    }

    @Override
    @Transactional(rollbackFor = ZooException.class)
    public RegisterDTO register(UtentiReq Ureq, ClientiReq Creq) throws ZooException {
        log.debug("register {} {}", Ureq, Creq);

        Utenti u = new Utenti();
        u.setUserName(Ureq.getUsername());
        u.setEmail(Ureq.getEmail());
        u.setPwd(Ureq.getPwd());
        u.setIsActive(false);
        u.setRole(Roles.valueOf(Ureq.getRole().toUpperCase()));
        u.setIsValidate(false);
        

        u = repoU.save(u);

        Clienti c = new Clienti();
        c.setNome(Creq.getNome());
        c.setCognome(Creq.getCognome());
        c.setIndirizzo(Creq.getIndirizzo());
        c.setCap(Creq.getCap());
        c.setComune(Creq.getComune());
        c.setTelefono(Creq.getTelefono());
        c.setProvinca(Creq.getProvincia());
        
        c.setUtente(u); 
        u.setCliente(c);
        c = repoC.save(c);
        
        Carrelli carrello = new Carrelli();
        carrello.setCliente(c);
        repoCa.save(carrello);

        return Mapper.buildRegisterDTO(c, u);
    }

    @Transactional(rollbackFor = ZooException.class)
	@Override
	public void changePwd(UtentiReq req) throws ZooException {
		Utenti u = repoU.findByUserName(req.getUsername())
		           .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

		   if (!u.getPwd().equals(req.getOldPwd()))
		       throw new ZooException("pwd_ntcrct");

		   Optional.ofNullable(req.getNewPwd())
			.ifPresentOrElse(pwd -> {
				u.setPwd(req.getNewPwd());
			}, () -> { 
				throw new RuntimeException(msgS.get("user_no_newpwd"));
			});
		   repoU.save(u);
	}
    
    @Override
    public List<UtentiDTO> find(UtentiReq req) {

        log.debug("find con filtri {}", req);

        return repoU.findAll(UtentiSpecification.filterByParams(req))
                .stream()
                .map(Mapper::buildUtentiDTO)
                .toList();
    }
    
    @Override
	public void sendValidation(String userName) throws Exception {
		log.debug("sendValidation {}", userName);

		Utenti ut = repoU.findById(userName)
				.orElseThrow(() -> new ZooException(msgS.get("user_ntfnd")));
		sendMailValidation(ut);

	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void emailValidate(String userName) throws Exception {
		log.debug("emailValidate {}", userName);
		
		Utenti ut = repoU.findById(userName)
				.orElseThrow(() -> new ZooException(msgS.get("user_ntfnd")));	
		ut.setIsValidate(true);
		repoU.save(ut);
		
	}
	
	@Override
	public void resetPassword(UtentiReq req) throws Exception {
		log.debug("resetPssword {}", req);
		Utenti ut = repoU.findById(req.getUsername())
				.orElseThrow(() -> new ZooException(msgS.get("user_ntfnd")));

		Optional.ofNullable(req.getNewPwd())
			.ifPresentOrElse(pwd -> {
				ut.setPwd(req.getNewPwd());
			}, () -> { 
				throw new RuntimeException(msgS.get("user_no_newpwd"));
			});
		
		repoU.save(ut);

		
	}


	@Override
	public void sendResetPassword(String userName) throws Exception {
	    log.debug("sendResetPassword {}", userName);

	    Utenti ut = repoU.findById(userName)
	            .orElseThrow(() -> new ZooException(msgS.get("user_ntfnd")));

	    String link = resetPasswordURL + ut.getUserName();

	    String template = loadTemplate("mail/reset-password-email.html");
	    String body = fillTemplate(template, ut.getUserName(), link);

	    sendMail(ut, "Zoo Betacom Roma - Reset Password", body);
	}

	private void sendMailValidation(Utenti acc) throws Exception {
	    String link = validationURL + acc.getUserName();

	    String template = loadTemplate("mail/validation-email.html");
	    String body = fillTemplate(template, acc.getUserName(), link);

	    sendMail(acc, "Zoo Betacom Roma - Validazione Account", body);
	}

	private void sendMail(Utenti account, String oggetto, String body) throws Exception {
	    mailS.sendMail(MailReq.builder()
	            .to(account.getEmail())
	            .oggetto(oggetto)
	            .body(body)
	            .build()
	    );
	}

	private String loadTemplate(String path) throws Exception {
	    ClassPathResource resource = new ClassPathResource(path);
	    log.debug("Template path: {}", path);
	    log.debug("Template exists: {}", resource.exists());

	    try (InputStream is = resource.getInputStream()) {
	        byte[] bytes = is.readAllBytes();
	        return new String(bytes, StandardCharsets.UTF_8);
	    }
	}

	private String fillTemplate(String template, String username, String link) {
	    return template
	            .replace("{{username}}", username)
	            .replace("{{link}}", link);
	}


}