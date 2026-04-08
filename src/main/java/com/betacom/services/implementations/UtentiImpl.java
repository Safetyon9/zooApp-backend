package com.betacom.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.UtentiReqResp;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.LoginDTO;
import com.betacom.dto.outputs.RegisterDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.UtentiResp;
import com.betacom.enums.Roles;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.entity.commerce.Clienti;
import com.betacom.persistence.repository.IUtentiRepository;
import com.betacom.persistence.repository.commerce.IClientiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.IUtentiServices;
import com.betacom.utilities.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtentiImpl implements IUtentiServices {

	private final IUtentiRepository repoU;
    private final IClientiRepository repoC;

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

        repoU.save(u);
    }
    
    

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(UtentiReqResp req) throws ZooException {
        log.debug("update {}", req);

        Utenti u = repoU.findByUserName(req.getUserName())
                .orElseThrow(() -> new ZooException(msgS.get("usr_id_ntfnd")));
        
        Clienti c = repoC.findById(u.getCliente().getId())
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));
        
        
        if(req.getEmail() != null)
        	u.setEmail(req.getEmail());
        
        if(req.getNome() != null)
        	c.setNome(req.getNome());
        
        if(req.getCognome() != null)
        	c.setCognome(req.getCognome());
        
        if(req.getIndirizzo() != null)
        	c.setIndirizzo(req.getIndirizzo());
        
        if(req.getComune() != null)
        	c.setComune(req.getComune());
        
        if(req.getCap() != null)
        	c.setCap(req.getCap());
        
        if(req.getTelefono() != null)
        	c.setTelefono(req.getTelefono());
        
        if(req.getProvincia() != null)
        	c.setProvinca(req.getProvincia());

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
        
        Clienti c = repoC.findById(u.getCliente().getId())
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

        return Mapper.buildUtentiResp(u, c);
	}
    
    @Override
    public LoginDTO login(LoginReq req) throws ZooException {
        log.debug("login {}", req);
        Utenti utente = repoU.findByUserName(req.getUsername())
                .orElseThrow(() -> new ZooException(msgS.get("login_invalid")));

        if(!utente.getPwd().equals(req.getPwd()))
            throw new ZooException(msgS.get("login_invalid"));

        return LoginDTO.builder()
                .username(utente.getUserName())
                .ruolo(utente.getRole().toString())
                .build();
    }



    @Override
    @Transactional(rollbackFor = ZooException.class)
    public RegisterDTO register(UtentiReq Ureq, ClientiReq Creq) throws ZooException {
        log.debug("register {} {}", Ureq, Creq);

        Utenti u = new Utenti();
        u.setUserName(Ureq.getUsername());
        u.setEmail(Ureq.getEmail());
        u.setPwd(Ureq.getPwd());
        u.setRole(Roles.valueOf(Ureq.getRole().toUpperCase()));

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

        return Mapper.buildRegisterDTO(c, u);
    }


}