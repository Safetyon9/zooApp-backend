package com.betacom.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.betacom.persistence.entity.commerce.items.Biglietti;
import com.betacom.persistence.entity.commerce.items.Prodotti;
import com.betacom.persistence.repository.commerce.items.IBigliettiRepository;
import com.betacom.persistence.repository.commerce.items.IProdottiRepository;
import com.betacom.services.interfaces.IMessaggiServices;
import com.betacom.services.interfaces.IUploadServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadImpl implements IUploadServices {

    private final Path uploadPath;
    private final IMessaggiServices msgS;
    private final IProdottiRepository repoP;
    private final IBigliettiRepository bigP;

    public UploadImpl(
            @Value("${app.upload.dir:uploads}") String uploadDir,
            IMessaggiServices msgS,
            IProdottiRepository repoP,
            IBigliettiRepository bigP
    ) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        this.msgS = msgS;
        this.repoP = repoP;
        this.bigP = bigP;
        init();
    }

    private void init() {
        try {
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(msgS.get("upload_create"));
        }
    }

    @Override
    public String saveImage(MultipartFile file, Integer id, String tipo) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File vuoto");
        }

        String original = file.getOriginalFilename();
        if (original == null) {
            throw new RuntimeException("Nome file non valido");
        }

        String cleanName = original.replaceAll("\\s+", "_");

        String extension = "";
        if (cleanName.contains(".")) {
            extension = cleanName.substring(cleanName.lastIndexOf("."));
        }

        String baseName = cleanName.contains(".")
                ? cleanName.substring(0, cleanName.lastIndexOf("."))
                : cleanName;

        String uniqueName = baseName + "-" + UUID.randomUUID() + extension;

        Path destinationFile = uploadPath.resolve(uniqueName);

        try {
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(uniqueName)
                    .toUriString();

            if ("prodotto".equalsIgnoreCase(tipo)) {

                Prodotti p = repoP.findById(id)
                        .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

                p.setUrlImmagine(uniqueName);
                repoP.save(p);

            } else if ("biglietto".equalsIgnoreCase(tipo)) {

                Biglietti b = bigP.findById(id)
                        .orElseThrow(() -> new RuntimeException("Biglietto non trovato"));

                b.setUrlImmagine(uniqueName);
                bigP.save(b);
            }

            return uniqueName;

        } catch (IOException e) {
            throw new RuntimeException("Errore salvataggio file");
        }
    }

    @Override
    public void removeImage(String filename) throws Exception {
        Files.deleteIfExists(uploadPath.resolve(filename));
    }

    @Override
    public String buildUrl(String filename) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(filename)
                .toUriString();
    }
}