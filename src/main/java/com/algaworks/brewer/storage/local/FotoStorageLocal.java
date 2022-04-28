package com.algaworks.brewer.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage{
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal(){
		this.local = getDefault().getPath(System.getenv("HOME"), ".brewer");
		criarPastas();
	}
	
	private void criarPastas(){
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(logger.isDebugEnabled()){
				logger.debug("Foi criado as pastas para salvar fotos");
				logger.debug("Pasta default" + this.local.toAbsolutePath());
				logger.debug("pasta temporaria" + this.localTemporario.toAbsolutePath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao crar pasta para salvar foto", e);
		}
	}
	
	@Override
	public String salvarTemporariamente(MultipartFile[] files){
		String novoNome = null;
		if(files != null && files.length > 0){
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro salvando foto nas pasta temporaria", e);
			} 
			
		}
		return novoNome;
		
	}
	
	private String renomearArquivo(String nomeOriginal){
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		
		return novoNome;
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		// TODO Auto-generated method stub
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("erro lendo a foto temporaria", e);
		}
	}
	
}
