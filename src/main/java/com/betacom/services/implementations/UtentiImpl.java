package com.betacom.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.outputs.LoginDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.enums.Roles;
import com.betacom.exceptions.ZooException;
import com.betacom.persistence.entity.Utenti;
import com.betacom.persistence.repository.IUtentiRepository;
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

        Utenti u = new Utenti();
        u.setUserName(Ureq.getUsername());
        u.setEmail(Ureq.getEmail());
        u.setPwd(Ureq.getPwd());
        u.setRole(Roles.valueOf(Ureq.getRole().toUpperCase()));

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
        
        if(req.getPwd() != null)
        	u.setPwd(req.getPwd());
        
        if(req.getRole() != null)
        	u.setRole(Roles.valueOf(req.getRole().toUpperCase()));

        repoU.save(u);
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
}