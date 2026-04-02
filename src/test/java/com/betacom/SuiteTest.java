package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.utenti.UtentiImplTest;

@Suite
@SelectClasses({
	UtentiImplTest.class
})

public class SuiteTest {
	
}
