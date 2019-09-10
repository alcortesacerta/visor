package com.iecisa.sat.saie.service.abis.thread;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.iecisa.sat.saie.service.abis.client.dto.ColaAfisDTO;
import com.iecisa.sat.saie.service.rest.dao.FinEnrolamientoDAO;

public class ProcesadorColaAbisThread implements Runnable {
	
	private ThreadPoolExecutor threadpool;
	private boolean terminate = false;
	
	public void run() {		
		threadpool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
		
		System.out.println("procesador de cola arrancado");
		
		List<ColaAfisDTO> cola = new ArrayList<ColaAfisDTO>();
		//while(threadpool.getQueue().size() <= cola.size()){
		while(!terminate){
			if(cola.size() < 10){
				cola = FinEnrolamientoDAO.getInstance().getColaHuellasAfis();
			}
			int qsize = threadpool.getQueue().size();
			
			List<ColaAfisDTO> colaRemove = new ArrayList<ColaAfisDTO>();
			
			for(int i = qsize, j = 0; i < 10 && j < cola.size();i++,j++){
				ColaAfisDTO coladto = cola.get(j);
				if(coladto.getEstado() == 0 || coladto.getEstado() == 7){
					
					Calendar freintento = Calendar.getInstance();
					freintento.add(Calendar.MINUTE, (int)Math.pow(3, coladto.getReintento()));
					coladto.setFechaReintento(new Timestamp(freintento.getTimeInMillis()));
					coladto.setEstado(1);
					coladto.setReintento(coladto.getReintento()+1);
					FinEnrolamientoDAO.getInstance().actualizaColaHuellasAfis(coladto);
					colaRemove.add(coladto);
					
					Runnable procesador = new ConsultaAbisThread(coladto);
					threadpool.execute(procesador);
				}
			}
			
			cola.removeAll(colaRemove);
			try{
				if(cola.size() == 0){
					Thread.sleep(10000);
				}else{
					Thread.sleep(1000);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void terminar(){
		threadpool.shutdownNow();
		terminate = true;
	}

}
