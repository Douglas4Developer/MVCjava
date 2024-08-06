package com.doug.mvcjava.controllers;

import com.doug.mvcjava.beans.Projeto;
import com.doug.mvcjava.dao.ProjetoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionScope
public class ProjetoController {

    private final ProjetoDao projetoDao;

    @Autowired
    public ProjetoController(ProjetoDao projetoDao) {
        this.projetoDao = projetoDao;
    }

    @RequestMapping("/projetoform")
    public String showform(Model m) {
        m.addAttribute("command", new Projeto());
        return "projetoForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("projeto") Projeto projeto) {
        projetoDao.save(projeto);
        return new ModelAndView("redirect:/viewprojetos");
    }

    @RequestMapping("/viewprojetos")
    public ModelAndView viewprojetos() {
        return new ModelAndView("projetoList", "list", projetoDao.getProjetos());
    }
}
