package com.project.transacao_api.controller;

import com.project.transacao_api.business.services.TransacaoService;
import com.project.transacao_api.controller.dtos.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDto dto) {
        transacaoService.adicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> limparListaTransacoes() {
        transacaoService.limparListaTransacoes();
        return ResponseEntity.ok().build();
    }
}
