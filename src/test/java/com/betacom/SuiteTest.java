package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.biglietti.BigliettiControllerTest;
import com.betacom.biglietti.BigliettiServicesTest;
import com.betacom.bigliettigiornata.BigliettiGiornataControllerTest;
import com.betacom.bigliettigiornata.BigliettiGiornataImplTest;
import com.betacom.carrelli.CarrelliControllerTest;
import com.betacom.carrelli.CarrelliImplTest;
import com.betacom.clienti.ClientiControllerTest;
import com.betacom.clienti.ClientiServicesTest;
import com.betacom.corrieri.CorrieriControllerTest;
import com.betacom.corrieri.CorrieriImplTest;
import com.betacom.coupons.CouponsControllerTest;
import com.betacom.coupons.CouponsImplTest;
import com.betacom.eventi.EventiControllerTest;
import com.betacom.eventi.EventiServicesTest;
import com.betacom.giornate.GiornateControllerTest;
import com.betacom.giornate.GiornateServicesTest;
import com.betacom.metodiPagamento.MetodiPagamentoControllerTest;
import com.betacom.metodiPagamento.MetodiPagamentoImplTest;
import com.betacom.oggettiCarrelli.OggettiCarrelliControllerTest;
import com.betacom.oggettiCarrelli.OggettiCarrelliImplTest;
import com.betacom.oggettiordini.OggettiOrdiniControllerTest;
import com.betacom.oggettiordini.OggettiOrdiniImplTest;
import com.betacom.ordini.OrdiniControllerTest;
import com.betacom.ordini.OrdiniImplTest;
import com.betacom.pagamenti.PagamentiControllerTest;
import com.betacom.pagamenti.PagamentiImplTest;
import com.betacom.prodotti.ProdottiControllerTest;
import com.betacom.prodotti.ProdottiImplTest;
import com.betacom.recensioni.RecensioniControllerTest;
import com.betacom.recensioni.RecensioniImplTest;
import com.betacom.spedizioni.SpedizioniControllerTest;
import com.betacom.spedizioni.SpedizioniImplTest;
import com.betacom.utenti.UtentiControllerTest;
import com.betacom.utenti.UtentiImplTest;

@Suite
@SelectClasses({
	BigliettiServicesTest.class,
	BigliettiControllerTest.class,

	BigliettiGiornataImplTest.class,
	BigliettiGiornataControllerTest.class,

	CarrelliImplTest.class,
	CarrelliControllerTest.class,

	ClientiServicesTest.class,
	ClientiControllerTest.class,
	
	CorrieriImplTest.class,
	CorrieriControllerTest.class,

	CouponsImplTest.class,
	CouponsControllerTest.class,

	EventiServicesTest.class,
	EventiControllerTest.class,

	GiornateServicesTest.class,
	GiornateControllerTest.class,

	MetodiPagamentoImplTest.class,
	MetodiPagamentoControllerTest.class,

	OggettiCarrelliImplTest.class,
	OggettiCarrelliControllerTest.class,

	OggettiOrdiniImplTest.class,
	OggettiOrdiniControllerTest.class,

	OrdiniImplTest.class,
	OrdiniControllerTest.class,

	PagamentiImplTest.class,
	PagamentiControllerTest.class,

	ProdottiImplTest.class,
	ProdottiControllerTest.class,

	RecensioniImplTest.class,
	RecensioniControllerTest.class,

	SpedizioniImplTest.class,
	SpedizioniControllerTest.class,

	UtentiImplTest.class,
	UtentiControllerTest.class
})

public class SuiteTest {
	
}
