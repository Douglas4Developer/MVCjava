package com.doug.mvcjava.dao;

import com.doug.mvcjava.beans.Projeto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProjetoDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Projeto projeto) {
        String sql = "INSERT INTO projetos(titulo, descricao, data_inicio) VALUES(?, ?, ?)";
        return template.update(sql, projeto.getTitulo(), projeto.getDescricao(), projeto.getDataInicio());
    }

    public List<Projeto> getProjetos() {
        String sql = "SELECT * FROM projetos";
        return template.query(sql, new BeanPropertyRowMapper<>(Projeto.class));
    }
}
