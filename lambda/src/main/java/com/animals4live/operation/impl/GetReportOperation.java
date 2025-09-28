package com.animals4live.operation.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.animals4live.model.GetReportOutput;
import com.animals4live.operation.ReportOperation;
import software.amazon.smithy.java.core.schema.SerializableStruct;

public class GetReportOperation implements ReportOperation {

    @Override
    public SerializableStruct execute(String jsonRequestBody, Context context) {
        context.getLogger().log("CreateReportOperation starting");

        return GetReportOutput.builder()
                .reportId("444")
                .title("Some Title!")
                .bodyText("Very long report text...")
                .build();
    }
}

