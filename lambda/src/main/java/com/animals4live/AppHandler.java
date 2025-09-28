package com.animals4live;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.animals4live.service.RequestRouterService;
import com.animals4live.service.impl.RequestRouterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handler for requests to Lambda function.
 */
public class AppHandler implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private static final String TARGET_OPERATION_HEADER = "x-amz-target";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final RequestRouterService requestRouterService = new RequestRouterServiceImpl();


    public APIGatewayV2HTTPResponse handleRequest(final APIGatewayV2HTTPEvent input, final Context context) {
        final var logger = context.getLogger();
        final var requestHeaders = input.getHeaders();
        final var operationName = requestHeaders.get(TARGET_OPERATION_HEADER);

        try {
            logger.log(String.format("Input object: %s", objectMapper.writeValueAsString(input)));
            logger.log(String.format("Starting handling request. Operation: '%s'. Request ID: '%s'", operationName, context.getAwsRequestId()));
            final var response = requestRouterService.getOperation(operationName).execute(input.getBody(), context);
            final var responseBody = objectMapper.writeValueAsString(response);

            return buildResponse(200, responseBody);
        } catch (Throwable e) {
            logger.log(String.format("Failed to process the request. Error: '%s'", e.getMessage()));

            return buildResponse(400, "{ \"errorMessage\": \"Failed to process the request.\" }");
        }
    }

    private APIGatewayV2HTTPResponse buildResponse(int statusCode, String responseBody) {
        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(statusCode)
                .withHeaders(Map.of(
                        "Content-Type", "application/x-amz-json-1.1",
                        "Content-Length", String.valueOf(responseBody.length())))
                .withBody(responseBody)
                .build();
    }
}
