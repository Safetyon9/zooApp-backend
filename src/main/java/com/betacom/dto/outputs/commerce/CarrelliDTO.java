package com.betacom.dto.outputs.commerce;

import java.util.List;

<<<<<<< Updated upstream
import com.betacom.persistence.entity.commerce.OggettiCarrelli;

=======
>>>>>>> Stashed changes
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CarrelliDTO {
	private Integer id;
<<<<<<< Updated upstream
	private Integer clienteId;                        
=======
	private ClientiDTO cliente;                        
>>>>>>> Stashed changes
	private List<OggettiCarrelliDTO> oggettiCarrello;
}
