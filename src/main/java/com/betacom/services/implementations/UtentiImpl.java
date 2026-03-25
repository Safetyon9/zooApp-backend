package com.betacom.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.dto.inputs.UtentiReq;
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
    public void create (UtentiReq req){
        log.debug("create {}", req);

        if (repoU.findByUserNameIgnoreCase(req.getUserName()).isPresent()) {
            throw new ZooException(msgS.get("user_exists"));
        }
        
        if (repoU.findByEmail(req.getEmail()).isPresent()) {
            throw new ZooException(msgS.get("email_exists"));
        }

        Utenti u = new Utenti();
        u.setUserName(req.getUserName());
        u.setEmail(req.getEmail());
        u.setPwd(req.getPassword());
        u.setRole(Roles.valueOf(req.getRole().toUpperCase()));

        repoU.save(u);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void update(UtentiReq req) throws ZooException {
        log.debug("update {}", req);

        Utenti u = repoU.findById(req.getId())
                .orElseThrow(() -> new ZooException(msgS.get("usr_id_ntfnd")));
        
        if(req.getUserName() != null)
        	u.setUserName(req.getUserName());
        
        if(req.getEmail() != null)
        	u.setEmail(req.getEmail());
        
        if(req.getPassword() != null)
        	u.setPwd(req.getPassword());
        
        if(req.getRole() != null)
        	u.setRole(Roles.valueOf(req.getRole().toUpperCase()));

        repoU.save(u);
    }

    @Transactional(rollbackFor = ZooException.class)
    @Override
    public void delete(Integer id) throws ZooException {
        log.debug("delete {}", id);

        Utenti u = repoU.findById(id)
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

        Utenti u = repoU.findByUserNameIgnoreCase(userName)
                .orElseThrow(() -> new ZooException(msgS.get("usr_ntfnd")));

        return Mapper.buildUtentiDTO(u);
    }
}