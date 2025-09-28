package com.animals4live.service;

import com.animals4live.operation.ReportOperation;

public interface RequestRouterService {

    ReportOperation getOperation(String operationName);
}
