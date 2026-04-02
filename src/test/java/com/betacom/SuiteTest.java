package com.betacom;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.utenti.utentiImplTest;

@Suite
@SelectClasses({
	utentiImplTest.class
})

public class SuiteTest {
	
}
