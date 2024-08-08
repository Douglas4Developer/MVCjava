package com.doug.mvcjava.controllers;

import com.doug.mvcjava.beans.Projeto;
import com.doug.mvcjava.beans.Tarefa;
import com.doug.mvcjava.dao.ProjetoDao;
import com.doug.mvcjava.dao.TarefaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@SessionScope
public class ProjetoController {

    private final ProjetoDao projetoDao;
    private final TarefaDao tarefaDao;
    private Projeto projetoSelecionado;

    @Autowired
    private TarefaController tarefaController; // Injetando o TarefaController

    @Autowired
    public ProjetoController(ProjetoDao projetoDao, TarefaDao tarefaDao) {
        this.projetoDao = projetoDao;
        this.tarefaDao = tarefaDao;
    }

    public List<Projeto> getProjetos() {
        return projetoDao.getProjetos();
    }

    public String editarProjeto(Projeto projeto) {
        this.projetoSelecionado = projeto;
        return "editarProjeto"; // Navegação para a página de edição
    }

    public String excluirProjeto(Projeto projeto) {
        List<Tarefa> tarefasAssociadas = tarefaDao.getTarefasPorProjeto(projeto.getId());
        if (!tarefasAssociadas.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Não é possível excluir o projeto, pois existem tarefas associadas."));
            return null; // Retorna sem redirecionar para a lista de projetos
        }

        projetoDao.delete(projeto.getId());
        return "listarProjetos"; // Atualiza a lista de projetos
    }

    public String atualizarProjeto() {
        projetoDao.update(projetoSelecionado);
        return "listarProjetos"; // Volta para a lista após a atualização
    }

    public String cancelarEdicao() {
        return "listarProjetos"; // Retorna para a lista sem salvar
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public String visualizarTarefas(Projeto projeto) {
        this.projetoSelecionado = projeto;
        tarefaController.setProjetoSelecionado(projeto); // Define o projeto selecionado no TarefaController
        return "listarTarefas?faces-redirect=true";
    }

    public String novoProjeto() {
        this.projetoSelecionado = new Projeto(); // Inicializa um novo projeto
        return "cadastroProjeto?faces-redirect=true";
    }

    public String salvarNovoProjeto() {
        projetoDao.save(projetoSelecionado);
        return "listarProjetos?faces-redirect=true";
    }


    public String listarProjetos() {
        return "listarProjetos"; // Certifique-se de que isso corresponde ao 'from-outcome' no 'faces-config.xml'
    }


}
