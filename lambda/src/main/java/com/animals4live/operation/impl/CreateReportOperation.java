package com.animals4live.operation.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.animals4live.model.CreateReportOutput;
import com.animals4live.operation.ReportOperation;
import software.amazon.smithy.java.core.schema.SerializableStruct;

public class CreateReportOperation implements ReportOperation {

    @Override
    public SerializableStruct execute(String jsonRequestBody, Context context) {
        context.getLogger().log("CreateReportOperation starting");

        return CreateReportOutput.builder()
                .reportId("123")
                .bodyText("Some text")
                .title("The Great Title!")
                .build();
    }
}
