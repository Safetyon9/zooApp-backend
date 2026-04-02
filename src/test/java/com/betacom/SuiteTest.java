package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.biglietti.BigliettiControllerTest;
import com.betacom.bigliettigiornata.BigliettiGiornataControllerTest;
import com.betacom.carrelli.CarrelliControllerTest;
import com.betacom.clienti.ClientiControllerTest;
import com.betacom.corrieri.CorrieriControllerTest;
import com.betacom.corrieri.CorrieriImplTest;
import com.betacom.coupons.CouponsControllerTest;
import com.betacom.eventi.EventiControllerTest;
import com.betacom.giornate.GiornateControllerTest;
import com.betacom.metodiPagamento.MetodiPagamentoControllerTest;
import com.betacom.oggettiCarrelli.OggettiCarrelliControllerTest;
import com.betacom.oggettiordini.OggettiOrdiniControllerTest;
import com.betacom.oggettiordini.OggettiOrdiniImplTest;
import com.betacom.ordini.OrdiniControllerTest;
import com.betacom.ordini.OrdiniImplTest;
import com.betacom.pagamenti.PagamentiControllerTest;
import com.betacom.prodotti.ProdottiControllerTest;
import com.betacom.prodotti.ProdottiImplTest;
import com.betacom.recensioni.RecensioniControllerTest;
import com.betacom.recensioni.RecensioniImplTest;
import com.betacom.spedizioni.SpedizioniControllerTest;
import com.betacom.utenti.UtentiControllerTest;
import com.betacom.utenti.UtentiImplTest;

@Suite
@SelectClasses({
	BigliettiControllerTest.class,

	BigliettiGiornataControllerTest.class,

	CarrelliControllerTest.class,

	ClientiControllerTest.class,
	
	CorrieriImplTest.class,
	CorrieriControllerTest.class,
	
	CouponsControllerTest.class,

	EventiControllerTest.class,

	GiornateControllerTest.class,

	MetodiPagamentoControllerTest.class,

	OggettiCarrelliControllerTest.class,

	OggettiOrdiniImplTest.class,
	OggettiOrdiniControllerTest.class,

	OrdiniImplTest.class,
	OrdiniControllerTest.class,

	PagamentiControllerTest.class,

	ProdottiImplTest.class,
	ProdottiControllerTest.class,

	RecensioniControllerTest.class,

	SpedizioniControllerTest.class,

	UtentiImplTest.class,
	UtentiControllerTest.class
})

public class SuiteTest {
	
}
