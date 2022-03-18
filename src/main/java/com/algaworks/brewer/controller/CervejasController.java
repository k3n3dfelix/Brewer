package com.algaworks.brewer.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Cervejas;
import com.algaworks.brewer.repository.Estilos;

@Controller
public class CervejasController {
	
	private static final Logger logger = LoggerFactory.getLogger(CervejasController.class);
	
	//@Autowired
	//private Cervejas cervejas; 
	
	@Autowired
	private Estilos estilos;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja){
		
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		 //ArrayList<Estilo> estilos = new ArrayList<Estilo>();
		 estilos.findAll();
		 System.out.println(estilos);
		mv.addObject("sabores", Sabor.values()); //Passando um array de sabores para a view
		mv.addObject("estilos", estilos.findAll()); //Passando um obejeto do banco de estilos para a view
		mv.addObject("origens", Origem.values());
		//logger.error("Aqui é um log de erro");
		//logger.info("Aqui é um log de info");
		//Exemplo de logo de erro com debug
		if(logger.isDebugEnabled()){
			logger.error("Aqui é um log de erro");
		}
		
		//cervejas.findAll();
		//Optional<Cerveja> cervejaOptional = cervejas.findBySkuIgnoreCase("AAA111");
		//System.out.println(cervejaOptional.isPresent());
		
		return mv;
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes){
		
		/*if(result.hasErrors()){
			
			return novo(cerveja);
		} */
		//Salvar no banco de dados
		attributes.addFlashAttribute("mensagem","Cerveja salva com sucesso");
		System.out.println(">>>sku" + cerveja.getSabor());
		System.out.println(">>>sku" + cerveja.getOrigem());
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	@RequestMapping("/cervejas/cadastro")
	public String cadastro(){
		return "cerveja/cadastro-produto";
	}
}
