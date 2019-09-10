package com.iecisa.sat.saie.service.abis.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iecisa.sat.saie.dao.DBManagerDuplicados;
import com.iecisa.sat.saie.dao.DBManagerSaie;
import com.iecisa.sat.saie.service.abis.thread.ProcesadorColaAbisThread;

public class ColaAfisContextInitializer implements ServletContextListener {

	ProcesadorColaAbisThread procesadorCola;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		procesadorCola.terminar();
		DBManagerSaie.closePool();
		DBManagerDuplicados.closePool();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		procesadorCola = new ProcesadorColaAbisThread();
		new Thread(procesadorCola).start();
	}

}
