package com.betacom.dto.outputs.commerce;

<<<<<<< Updated upstream
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

=======
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

>>>>>>> Stashed changes
@Setter
@Getter
@Builder
public class OggettiCarrelliDTO {
	private Integer id;

	private Integer quantita;
	
<<<<<<< Updated upstream
	private BigDecimal prezzoUnitario;
	
	private BigDecimal prezzoTotale;
=======
	private Integer prezzoTotale;
>>>>>>> Stashed changes
	
    private Integer carrelloId;
	
    private Integer itemId;
}
