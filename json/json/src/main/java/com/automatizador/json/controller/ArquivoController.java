package com.automatizador.json.controller;
import com.automatizador.json.domain.ContatoData;
import com.automatizador.json.domain.ContatoDomain;
import com.automatizador.json.domain.ProdutoDomain;
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
        ContatoData contatoData = new ContatoData();
        ContatoDomain contatoDomain = new ContatoDomain();
        ProdutoDomain produtoDomain = new ProdutoDomain();
        contatoData.setData(contatoDomain);

        String contatoDataJson = contatoData.getClass().getSimpleName();

        contatoDomain.setName("Pedro");
        contatoDomain.setEndereço("Rua dos Bobos, Vale do Silêncio - SP");
        contatoDomain.setIdade(21);
        contatoDomain.setNacionalidade("brasileiro");
        contatoDomain.setSexo("masculino");
        contatoDomain.setProdutoDomain(produtoDomain);
        produtoDomain.setMarca("Baby Johson");
        produtoDomain.setPreço("R$ 35");
        produtoDomain.setValidade("20/10/2023");

        Map<String, ContatoDomain> dados = new HashMap<>();
        dados.put(contatoDataJson, contatoDomain);

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
