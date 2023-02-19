package com.automatizador.json.controller;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ArquivoController {

    @GetMapping("/gerar-arquivo-json")
    public void gerarArquivoJson(HttpServletResponse response) throws IOException {
        // Cria um objeto de exemplo
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "Pablo da Silva");
        dados.put("idade", 30);
        dados.put("endereco", "Rua dos Bobos, 0, Vila dos Pombos, São Paulo - SP, 01234-567");

        // Cria um objeto ObjectMapper para serializar em JSON
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Serializa o objeto em JSON
            String json = mapper.writeValueAsString(dados);

            // Cria um novo arquivo e escreve o conteúdo JSON
            FileWriter fileWriter = new FileWriter("arquivo.json");
            fileWriter.write(json);
            fileWriter.close();

            // Define o cabeçalho de resposta informando que é um arquivo JSON
            response.setContentType("application/json");
            // Envia o arquivo gerado como resposta
            response.setHeader("Content-disposition", "attachment; filename=arquivo.json");
            response.getWriter().write(json);

        } catch (IOException e) {
            // Retorna uma resposta com status de erro caso ocorra uma exceção
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao gerar arquivo JSON");
        }
    }
}
