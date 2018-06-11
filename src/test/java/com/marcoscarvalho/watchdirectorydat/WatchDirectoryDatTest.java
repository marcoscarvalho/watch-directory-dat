package com.marcoscarvalho.watchdirectorydat;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WatchDirectoryDatTest {

	private final Logger logger = LogManager.getLogger(getClass());

	@Before
	public void testApp() {
		logger.info("Inicializando Testes");
		assertTrue(true);
	}

	@Test
	public void test1() {
		assertTrue(true);
	}

	@After
	public void finalizando() {
		logger.info("Terminando Testes");
		assertTrue(true);
	}
}
