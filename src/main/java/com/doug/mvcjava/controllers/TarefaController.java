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
    private Projeto projetoSelecionado; // Adiciona uma referência ao projeto

    @Autowired
    public TarefaController(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }

    // Método agora aceita um argumento
    public List<Tarefa> tarefasPorProjeto(Integer projetoId) {
        if (projetoId != null) {
            return tarefaDao.getTarefasPorProjeto(projetoId);
        }
        return null;
    }

    public String editarTarefa(Tarefa tarefa) {
        this.tarefaSelecionada = tarefa;
        return "editarTarefa"; // Navegação para a página de edição
    }

    public String excluirTarefa(Tarefa tarefa) {
        tarefaDao.delete(tarefa.getId());
        return "listarTarefas"; // Atualiza a lista de tarefas
    }

    public String atualizarTarefa() {
        tarefaDao.update(tarefaSelecionada);
        return "listarTarefas"; // Volta para a lista após a atualização
    }

    public String novaTarefa() {
        this.tarefaSelecionada = new Tarefa();
        this.tarefaSelecionada.setProjeto(projetoSelecionado); // Associa ao projeto atual
        return "cadastroTarefa"; // Navegação para a página de cadastro de tarefa
    }

    public String salvarTarefa() {
        if (tarefaSelecionada != null) {
            tarefaDao.save(tarefaSelecionada);
            return "listarTarefas"; // Volta para a lista após salvar
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Tarefa não inicializada."));
            return null; // Permanecer na mesma página
        }
    }

    // Getter e Setter para tarefaSelecionada
    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public void setProjetoSelecionado(Projeto projeto) {
        this.projetoSelecionado = projeto;
    }
}
