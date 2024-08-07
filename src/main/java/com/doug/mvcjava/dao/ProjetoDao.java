package com.doug.mvcjava.dao;

import com.doug.mvcjava.beans.Projeto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjetoDao {

    private final JdbcTemplate template;

    public ProjetoDao(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Projeto projeto) {
        String sql = "INSERT INTO projeto (titulo, descricao, data_inicio) VALUES (?, ?, ?)";
        return template.update(sql, projeto.getTitulo(), projeto.getDescricao(), projeto.getDataInicio());
    }

    public List<Projeto> getProjetos() {
        String sql = "SELECT * FROM projeto";
        return template.query(sql, new BeanPropertyRowMapper<>(Projeto.class));
    }

    public int update(Projeto projeto) {
        String sql = "UPDATE projeto SET titulo = ?, descricao = ?, data_inicio = ? WHERE id = ?";
        return template.update(sql, projeto.getTitulo(), projeto.getDescricao(), projeto.getDataInicio(), projeto.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM projeto WHERE id = ?";
        return template.update(sql, id);
    }
}
