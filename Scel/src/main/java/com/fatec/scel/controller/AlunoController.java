package com.fatec.scel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;
import com.fatec.scel.model.Livro;

@RestController
@RequestMapping(path = "/alunos")
public class AlunoController {
	@Autowired
	private AlunoRepository repository;
	
	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("consultaAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastraLivro(Aluno aluno) {
		ModelAndView mv = new ModelAndView("cadastrarAluno");
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultrAluno");
		if (result.hasErrors()) {
			return new ModelAndView("cadastrarAluno");
		}
		try {
			Aluno jaExiste = null;
			jaExiste = repository.findByRa(aluno.getRa());
			if (jaExiste == null) {
				repository.save(aluno);
				modelAndView = new ModelAndView("consultaAluno");
				modelAndView.addObject("alunos", repository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("cadastrarAluno");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " + e.getMessage());
			return modelAndView;
		}
	}
	
	@GetMapping("/edit/{ra}")
	public ModelAndView mostraFormAdd(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("atualizaAluno");
		modelAndView.addObject("aluno", repository.findByRa(ra));
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", repository.findAll());
		return modelAndView;
	}
	
	@PostMapping("/update/{id}")
	public ModelAndView atualiza(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result) {

		if (result.hasErrors()) {
			aluno.setId(id);
			return new ModelAndView("atualizaAluno");
		}
		Aluno umAluno = repository.findById(id).get();
		umAluno.setEmail(aluno.getEmail());
		umAluno.setRa(aluno.getRa());
		umAluno.setNome(aluno.getNome());
		repository.save(umAluno);
		ModelAndView modelAndView = new ModelAndView("consultaAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}
	
}
