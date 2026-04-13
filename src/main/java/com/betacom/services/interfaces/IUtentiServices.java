package com.betacom.services.interfaces;

import java.util.List;

import com.betacom.dto.inputs.LoginReq;
import com.betacom.dto.inputs.UtentiReq;
import com.betacom.dto.inputs.commerce.ClientiReq;
import com.betacom.dto.outputs.LoginDTO;
import com.betacom.dto.outputs.RegisterDTO;
import com.betacom.dto.outputs.UtentiDTO;
import com.betacom.dto.outputs.UtentiResp;
import com.betacom.exceptions.ZooException;

public interface IUtentiServices {

    void create(UtentiReq req) throws ZooException;
    
    void update(UtentiReq req) throws ZooException;
    
    void Allupdate(UtentiReq Ureq, ClientiReq Creq) throws ZooException;
    
    void delete(String username) throws ZooException;

    List<UtentiDTO> list() throws ZooException;

    UtentiDTO getByUserName(String userName) throws ZooException;
    
    UtentiResp getAllByUser(String userName) throws ZooException;
    
    LoginDTO login(LoginReq req) throws ZooException;
    
    void logout(String userName) throws ZooException; 
	
	RegisterDTO register(UtentiReq Ureq, ClientiReq Creq) throws ZooException;
	
	void changePwd(UtentiReq req) throws ZooException;

	List<UtentiDTO> find(UtentiReq req);

	void sendValidation(String userName) throws Exception;

	void emailValidate(String userName) throws Exception;


	void resetPassword(UtentiReq req) throws Exception;

	void passwordDimenticata(String email) throws Exception;
	
	
	
	
}