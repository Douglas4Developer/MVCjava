package com.doug.mvcjava.dao;

import com.doug.mvcjava.beans.Tarefa;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class TarefaDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas(titulo, descricao, prioridade, estimativa_horas, projeto_id) VALUES(?, ?, ?, ?, ?)";
        return template.update(sql, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getEstimativaHoras(), tarefa.getProjeto().getId());
    }

    public List<Tarefa> getTarefas() {
        String sql = "SELECT * FROM tarefa";
        return template.query(sql, new BeanPropertyRowMapper<>(Tarefa.class));
    }
}
