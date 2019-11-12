package com.fatec.scel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;
import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.model.EmprestimoRepository;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.model.Servico;

@RestController
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AlunoRepository usuarioRepository;
	

	private Servico Servico;


	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimo", emprestimoRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
		ModelAndView mv = new ModelAndView("RegistrarEmprestimo");
		mv.addObject("emprestimo", emprestimo);
		return mv;
	}
	@PostMapping("/save")
	public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		if (result.hasErrors()) {
			return new ModelAndView("RegistrarEmprestimo");
		}
		try {
			Emprestimo jaExiste = null;
			jaExiste = emprestimoRepository.findByIsbn(emprestimo.getIsbn());
			if (jaExiste == null) {
				emprestimoRepository.save(emprestimo);
				modelAndView = new ModelAndView("consultarEmprestimo");
				modelAndView.addObject("emprestimo", emprestimoRepository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("RegistrarEmprestimo");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " + e.getMessage());
			return modelAndView;
		}
	}
	
}