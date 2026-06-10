package com.example.ticket_hub.controller;

import com.example.ticket_hub.model.Evento;
import com.example.ticket_hub.model.Usuario;
import com.example.ticket_hub.repository.EventoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/eventos")
public class AdminController {

    @Autowired
    private EventoRepository eventoRepository;

    private boolean isAdmin(HttpSession session) {
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        if (logado == null) {
            return false;
        }
        return logado.getUsuario().equals("admin");
    }

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("eventos", eventoRepository.findAll());
        model.addAttribute("evento", new Evento());
        return "admin-eventos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, HttpSession session, Model model) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento == null) {
            return "redirect:/admin/eventos";
        }

        model.addAttribute("eventos", eventoRepository.findAll());
        model.addAttribute("evento", evento);
        return "admin-eventos";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam(required = false) Long id,
                         @RequestParam String titulo,
                         @RequestParam String descricao,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime data,
                         @RequestParam String local,
                         @RequestParam double preco,
                         @RequestParam int capacidade,
                         @RequestParam int ingressosDisponiveis,
                         @RequestParam String imagem,
                         HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }

        Evento evento;
        if (id != null) {
            evento = eventoRepository.findById(id).orElse(null);
            if (evento == null) {
                return "redirect:/admin/eventos";
            }
        } else {
            evento = new Evento();
        }

        evento.setTitulo(titulo);
        evento.setDescricao(descricao);
        evento.setData(data);
        evento.setLocal(local);
        evento.setPreco(preco);
        evento.setCapacidade(capacidade);
        evento.setIngressosDisponiveis(ingressosDisponiveis);
        evento.setImagem(imagem);
        eventoRepository.save(evento);

        return "redirect:/admin/eventos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/login";
        }
        eventoRepository.deleteById(id);
        return "redirect:/admin/eventos";
    }
}
