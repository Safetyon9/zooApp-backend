package com.betacom.controllers.commerce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.betacom.response.Resp;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.IUploadServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/upload")
public class UploadController {

    private final IUploadServices uplS;
    private final IMessaggiServices msgS;

    @PostMapping(value = "/image", consumes = "multipart/form-data")
    public ResponseEntity<Resp> uploadImage(
            @RequestParam MultipartFile file,
            @RequestParam Integer id,
            @RequestParam String tipo) {

        Resp r = new Resp();

        try {
            if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
                r.setMsg(msgS.get("upload_invalid"));
                return ResponseEntity.badRequest().body(r);
            }

            String filename = uplS.saveImage(file, id, tipo);

            r.setMsg(filename);
            return ResponseEntity.status(HttpStatus.CREATED).body(r);

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            return ResponseEntity.internalServerError().body(r);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ricevuta")
    public ResponseEntity<Resp> uploadRicevutaPdf(
            @RequestParam MultipartFile file,
            @RequestParam Integer pagamentoId,
            @RequestParam String idRicevuta) {

        Resp r = new Resp();

        try {
            if (file.getContentType() == null || !file.getContentType().equalsIgnoreCase("application/pdf")) {
                r.setMsg("Il file deve essere un PDF");
                return ResponseEntity.badRequest().body(r);
            }

            String url = uplS.saveRicevutaPdf(file, pagamentoId, idRicevuta);

            r.setMsg(url);
            
            
            
            return ResponseEntity.status(HttpStatus.CREATED).body(r);

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            return ResponseEntity.internalServerError().body(r);
        }
    }

    @GetMapping("/getUrl")
    public ResponseEntity<Resp> getUrl(@RequestParam String filename) {

        Resp r = new Resp();
        r.setMsg(uplS.buildUrl(filename));

        return ResponseEntity.ok(r);
    }
}