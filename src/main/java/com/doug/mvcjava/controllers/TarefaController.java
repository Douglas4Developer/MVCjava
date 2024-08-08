package com.doug.mvcjava.controllers;

import com.doug.mvcjava.beans.Projeto;
import com.doug.mvcjava.beans.Tarefa;
import com.doug.mvcjava.dao.TarefaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@SessionScope
public class TarefaController {

    private final TarefaDao tarefaDao;
    private Tarefa tarefaSelecionada;
    private Projeto projetoSelecionado;

    @Autowired
    public TarefaController(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }

    public List<Tarefa> getTarefasPorProjeto() {
        if (projetoSelecionado != null) {
            return tarefaDao.getTarefasPorProjeto(projetoSelecionado.getId());
        }
        return null;
    }

    public String editarTarefa(Tarefa tarefa) {
        this.tarefaSelecionada = tarefa;
        if (tarefa.getProjeto() == null && projetoSelecionado != null) {
            tarefaSelecionada.setProjeto(projetoSelecionado);
        }
        return "editarTarefa"; // Navegação para a página de edição
    }

    public String excluirTarefa(Tarefa tarefa) {
        tarefaDao.delete(tarefa.getId());
        return "listarTarefas"; // Atualiza a lista de tarefas
    }

    public String atualizarTarefa() {
        if (tarefaSelecionada != null && tarefaSelecionada.getProjeto() != null) {
            tarefaDao.update(tarefaSelecionada);
            return "listarTarefas"; // Volta para a lista após a atualização
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tarefa ou projeto não inicializados."));
            return null; // Permanecer na mesma página
        }
    }


    public String cancelarEdicao() {
        return "listarTarefas"; // Retorna para a lista sem salvar
    }

    public String novaTarefa() {
        this.tarefaSelecionada = new Tarefa();
        if (projetoSelecionado != null) {
            this.tarefaSelecionada.setProjeto(projetoSelecionado); // Associa ao projeto atual
        }
        return "cadastroTarefa"; // Navegação para a página de cadastro de tarefa
    }


    public String salvarTarefa() {
        if (tarefaSelecionada != null && tarefaSelecionada.getProjeto() != null) {
            tarefaDao.save(tarefaSelecionada);
            return "listarTarefas"; // Volta para a lista após salvar
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Projeto ou Tarefa não inicializados."));
            return null; // Permanecer na mesma página
        }
    }

    // Getter e Setter para tarefaSelecionada e projetoSelecionado
    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }
}
