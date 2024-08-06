package com.doug.mvcjava.controllers;

import com.doug.mvcjava.beans.Projeto;
import com.doug.mvcjava.dao.ProjetoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@SessionScope
public class ProjetoController {

    private final ProjetoDao projetoDao;
    private Projeto projetoSelecionado;

    @Autowired
    public ProjetoController(ProjetoDao projetoDao) {
        this.projetoDao = projetoDao;
    }

    public List<Projeto> getProjetos() {
        return projetoDao.getProjetos();
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public String salvarProjeto() {
        projetoDao.save(projetoSelecionado);
        return "listarProjetos"; // Nome da página para redirecionar
    }

    public String cancelarEdicao() {
        projetoSelecionado = null;
        return "listarProjetos"; // Nome da página para redirecionar
    }
}
