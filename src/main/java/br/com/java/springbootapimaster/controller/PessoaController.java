package br.com.java.springbootapimaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.java.springbootapimaster.entity.Pessoa;
import br.com.java.springbootapimaster.repository.PessoaRepository;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class PessoaController {
    
    @Autowired
    private PessoaRepository _pessoaRepository;

    @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
    public List<Pessoa> Get() {
        return _pessoaRepository.findAll();
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if (pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa) {
        return _pessoaRepository.save(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa) {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if (oldPessoa.isPresent()) {
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}