//model é a camada de domínio, onde ficam as classes que representam as entidades do sistema (Evento, Usuario, Pedido)

package com.example.ticket_hub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private int ingressosDisponiveis;

    private String imagem;
}
