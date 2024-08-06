package com.doug.mvcjava.controllers;

import com.doug.mvcjava.beans.Tarefa;
import com.doug.mvcjava.dao.TarefaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@SessionScope
public class TarefaController {

    private final TarefaDao tarefaDao;
    private Tarefa tarefaSelecionada;

    @Autowired
    public TarefaController(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }

    public List<Tarefa> getTarefas() {
        return tarefaDao.getTarefas();
    }

    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public String salvarTarefa() {
        tarefaDao.save(tarefaSelecionada);
        return "listarTarefas"; // Nome da página para redirecionar
    }

    public String cancelarEdicao() {
        tarefaSelecionada = null;
        return "listarTarefas"; // Nome da página para redirecionar
    }
}
