package com.example.ticket_hub.controller;

import com.example.ticket_hub.enums.FormaPagamento;
import com.example.ticket_hub.model.Pedido;
import com.example.ticket_hub.repository.EventoRepository;
import com.example.ticket_hub.repository.PedidoRepository;
import com.example.ticket_hub.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/pagamento/{eventoId}")
    public String pagamento(@PathVariable Long eventoId, Model model) {
        model.addAttribute("evento", eventoRepository.findById(eventoId).orElseThrow());
        return "pagamento";
    }

    @PostMapping("/pedidos")
    public String criar(@RequestParam Long eventoId,
                        @RequestParam String nomeComprador,
                        @RequestParam String emailComprador,
                        @RequestParam int quantidade,
                        @RequestParam FormaPagamento formaPagamento,
                        Model model) {
        try {
            Pedido pedido = pedidoService.criar(eventoId, nomeComprador, emailComprador, quantidade, formaPagamento);
            return "redirect:/confirmacao/" + pedido.getId();
        } catch (RuntimeException e) {
            model.addAttribute("evento", eventoRepository.findById(eventoId).orElseThrow());
            model.addAttribute("erro", e.getMessage());
            return "pagamento";
        }
    }

    @GetMapping("/confirmacao/{pedidoId}")
    public String confirmacao(@PathVariable Long pedidoId, Model model) {
        model.addAttribute("pedido", pedidoRepository.findById(pedidoId).orElseThrow());
        return "confirmacao";
    }

    @GetMapping("/meus-ingressos")
    public String meusIngressos(@RequestParam(required = false) String email, Model model) {
        if (email != null && !email.isBlank()) {
            model.addAttribute("pedidos", pedidoRepository.findByEmailComprador(email));
            model.addAttribute("email", email);
        }
        return "meus-ingressos";
    }
}
