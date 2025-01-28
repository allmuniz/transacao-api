package com.project.transacao_api.business.services;

import com.project.transacao_api.controller.dtos.TransacaoRequestDto;
import com.project.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDto> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDto dto) {

        log.info("Iniciado o processamento da transação {}", dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data atual");
        } else if (dto.valor() < 0) {
            log.error("Valor não pode ser menor que zero");
            throw new UnprocessableEntity("Valor não pode ser menor que zero");
        }
        listaTransacoes.add(dto);
        log.info("Transação adicionado com sucesso");
    }

    public  void limparListaTransacoes() {
        log.info("Iniciado processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDto> buscarTransacoes(Integer intervaloBusca){
        log.info("Inicadas buscas as transação do intervalo de {} segundos", intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Transação buscadas com sucesso");
        return listaTransacoes.stream().filter(t -> t.dataHora().isAfter(dataHoraIntervalo)).toList();
    }
}
