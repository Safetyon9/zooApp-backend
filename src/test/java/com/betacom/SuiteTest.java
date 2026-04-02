package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

<<<<<<< HEAD
import com.betacom.bigliettigiornata.BigliettiGiornataControllerTest;
import com.betacom.bigliettigiornata.BigliettiGiornataImplTest;
=======
>>>>>>> 8487bcba1e431c95d81b7b9d24036ce0ab95d05f
import com.betacom.utenti.UtentiImplTest;

@Suite
@SelectClasses({
<<<<<<< HEAD
	BigliettiGiornataImplTest.class,
	BigliettiGiornataControllerTest.class,
	
	UtentiImplTest.class,
=======
	UtentiImplTest.class
>>>>>>> 8487bcba1e431c95d81b7b9d24036ce0ab95d05f
})

public class SuiteTest {
	
}
