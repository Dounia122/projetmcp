package org.example.mcpclientt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.CommandLineRunner;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class McpClienttApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClienttApplication.class, args);
    }

    @Bean
    CommandLineRunner run(List<McpSyncClient> clients) {
        return args -> {
            clients.forEach(client -> {
               client.listTools().tools().forEach(tool -> {
                   System.out.println("***************");
                   System.out.println(tool.name());
                   System.out.println(tool.inputSchema());
                   System.out.println(tool.description());
                   System.out.println("************");
               });
            });
            var params= """
                    {
                     "companyName":"OCP"
                    }
                    """;
            McpSchema.CallToolResult result = clients.get(0).callTool(new McpSchema.CallToolRequest("getStockByCompany", params));
            System.out.println(result.content().get(0).type());
        };
    }
}