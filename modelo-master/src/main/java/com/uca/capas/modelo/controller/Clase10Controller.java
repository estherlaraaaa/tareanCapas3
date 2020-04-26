package com.uca.capas.modelo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Clase10Controller {
	
	@RequestMapping("/ingresar")
	public String index() {
		/* No es necesario poner la extension .html a index, ya que el metodo
		 * templateResolver.setSuffix(".html") sirve para asumir dicha extension.
		 * Como solo vamos a redirigir a la pagina index.html no es necesario un 
		 * objeto ModelAndView, al devolver un tipo de dato String, Spring automaticamente
		 * asume que es una vista la que se quiere devolver.
		 */
		return "clases/clase10/index";
	}
	
	@RequestMapping("/ingreso")
	public ModelAndView ingreso(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String nacimiento = request.getParameter("nacimiento");
		String lugarnaci = request.getParameter("lugarnaci");
		String colegio = request.getParameter("colegio");
		String tel = request.getParameter("telefono");
		String cel = request.getParameter("celular");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d1 = sdf.parse(nacimiento);
		Date d2 = sdf.parse("2003-01-01"); 
		
	
		ArrayList<String> estudiante = new ArrayList<>();
		String estudianteCorrecto = "Estudiante ingresado con éxito";
		
		
		if(nombre.length()<1 || nombre.length()>25) { 
			estudiante.add("el nombre debe ser mínimo 1 caracter, máximo 25");
		}
		
		if(apellido.length()<1 || apellido.length()>25) {
			estudiante.add("el apellido debe ser mínimo 1 caracter, máximo 25");
		}
		
		if(d1.compareTo(d2)<0) {
			estudiante.add("la fecha no puede ser menor al 1 de enero de 2003");
		} 
		
		if(lugarnaci.length()<1 || lugarnaci.length()>25) {
			estudiante.add("el lugar de nacimiento debe ser mínimo 1 carácter y máximo 25 caracteres");
		}
		if(colegio.length()<1 || colegio.length()>100) {
			estudiante.add("el colegio debe ser mínimo 1 carácter y máximo 100 caracteres");
		}
		if(tel.length()!= 8) {
			estudiante.add("el telefono debe tener 8 números exactamente");
		}
		if(cel.length()!= 8) {
			estudiante.add("el celular debe ser 8 números exactamente");
		}
	
		if(estudiante.isEmpty()) {
			mav.addObject("Estudiante ingresado con éxito", estudianteCorrecto);
			mav.setViewName("clases/clase10/resultado");

		}else {
			mav.addObject("errores", estudiante);
			mav.setViewName("clases/clase10/errores");
		}

		return mav;
		
	}


}
