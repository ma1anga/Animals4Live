package com.animals4live.service.impl;

import com.animals4live.operation.ReportOperation;
import com.animals4live.operation.impl.CreateReportOperation;
import com.animals4live.operation.impl.GetReportOperation;
import com.animals4live.service.RequestRouterService;

public class RequestRouterServiceImpl implements RequestRouterService {

    @Override
    public ReportOperation getOperation(String operationName) {
        if (operationName == null || operationName.isBlank()) {
            throw new IllegalArgumentException("Operation must not be null or empty");
        }

        return switch (operationName) {
            case "Animals4LiveService.CreateReport" -> new CreateReportOperation();
            case "Animals4LiveService.GetReport" -> new GetReportOperation();
            default -> throw new IllegalArgumentException("Unsupported operation: " + operationName);
        };
    }
}
