package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.bigliettigiornata.BigliettiGiornataControllerTest;
import com.betacom.bigliettigiornata.BigliettiGiornataImplTest;
import com.betacom.utenti.UtentiImplTest;

@Suite
@SelectClasses({
	BigliettiGiornataImplTest.class,
	BigliettiGiornataControllerTest.class,
	
	UtentiImplTest.class,
})

public class SuiteTest {
	
}
