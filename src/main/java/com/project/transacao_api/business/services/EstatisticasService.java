package com.project.transacao_api.business.services;

import com.project.transacao_api.controller.dtos.EstatisticasResponsetDto;
import com.project.transacao_api.controller.dtos.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    private final TransacaoService transacaoService;

    public EstatisticasResponsetDto calcularEstatisticas(Integer intervaloBusca) {

        log.info("Iniciando o calculo das estatisticas de transações pelo período de " + intervaloBusca + " segundos");

        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor).summaryStatistics();

        log.info("Estatisticas calculadas com sucesso");

        return new EstatisticasResponsetDto(estatisticasTransacoes.getCount(), estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(), estatisticasTransacoes.getMin(), estatisticasTransacoes.getMax());
    }
}
