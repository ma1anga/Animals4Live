package com.animals4live.operation;

import com.amazonaws.services.lambda.runtime.Context;
import software.amazon.smithy.java.core.schema.SerializableStruct;

public interface ReportOperation {

    SerializableStruct execute(String jsonRequestBody, Context context);
}
