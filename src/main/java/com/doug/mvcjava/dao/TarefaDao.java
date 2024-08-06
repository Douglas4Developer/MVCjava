package com.doug.mvcjava.dao;

import com.doug.mvcjava.beans.Tarefa;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TarefaDao {

    private final JdbcTemplate template;

    public TarefaDao(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (titulo, descricao, prioridade, estimativa_horas, projeto_id) VALUES (?, ?, ?, ?, ?)";
        return template.update(sql, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getEstimativaHoras(), tarefa.getProjeto().getId());
    }

    public List<Tarefa> getTarefas() {
        String sql = "SELECT * FROM tarefa";
        return template.query(sql, new BeanPropertyRowMapper<>(Tarefa.class));
    }

    public int update(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET titulo = ?, descricao = ?, prioridade = ?, estimativa_horas = ?, projeto_id = ? WHERE id = ?";
        return template.update(sql, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getEstimativaHoras(), tarefa.getProjeto().getId(), tarefa.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";
        return template.update(sql, id);
    }

    public List<Tarefa> getTarefasPorProjeto(int projetoId) {
        String sql = "SELECT * FROM tarefa WHERE projeto_id = ?";
        return template.query(sql, new Object[]{projetoId}, new BeanPropertyRowMapper<>(Tarefa.class));
    }

}
