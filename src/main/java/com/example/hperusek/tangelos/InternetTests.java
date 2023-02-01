/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hperusek.tangelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hperusek
 */
@RestController
public class InternetTests {
    
    @GetMapping("/activity")
    public Object getQuote() {
        try {
            String url = "https://www.boredapi.com/api/activity";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Print the whole response to console test.
            System.out.println(root);

            //Parse out the most important info from the response.
            String quoteAuthor = root.get("activity").asText();
            String quoteContent = root.get("type").asText();
            System.out.println("BTC to USD: " + quoteAuthor);
            System.out.println("Quote: " + quoteContent);

            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(TangelosApplication.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /quote";
        }
    }
    
}
