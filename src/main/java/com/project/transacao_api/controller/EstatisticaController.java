package com.project.transacao_api.controller;

import com.project.transacao_api.business.services.EstatisticasService;
import com.project.transacao_api.controller.dtos.EstatisticasResponsetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticasResponsetDto> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticas(intervaloBusca));
    }
}
