package com.animals4live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.animals4live.server.model.CreateReportInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    public APIGatewayV2HTTPResponse handleRequest(final APIGatewayV2HTTPEvent input, final Context context) {
        final ObjectMapper objectMapper = new ObjectMapper();

        var logger = context.getLogger();

        try {
            logger.log(new ObjectMapper().writeValueAsString(input));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        CreateReportInput createReportInput = CreateReportInput.builder()
                .bodyText("new Text")
                .title("TITLE!!!")
                .build();

        context.getLogger().log("Title: " + createReportInput.getTitle());
        context.getLogger().log("bodyText: " + createReportInput.getBodyText());

        try {
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);

            return APIGatewayV2HTTPResponse.builder().build();
        } catch (IOException e) {
            return APIGatewayV2HTTPResponse.builder().build();
        }
    }

    private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
