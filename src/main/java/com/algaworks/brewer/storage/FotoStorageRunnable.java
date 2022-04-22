package com.algaworks.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<String> resultado;
	
	

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<String> resultado) {
		super();
		this.files = files;
		this.resultado = resultado;
	}



	@Override
	public void run() {
		System.out.println("file" + files[0].getSize());
		resultado.setResult("Ok, Foto recebida!");

	}

}
