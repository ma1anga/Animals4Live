package com.animals4live;

import com.animals4live.server.model.CreateReportInput;
import com.animals4live.server.model.CreateReportOutput;
import com.animals4live.server.model.GetReportInput;
import com.animals4live.server.model.GetReportOutput;
import com.animals4live.server.service.Animals4LiveService;
import com.animals4live.server.service.CreateReportOperation;
import com.animals4live.server.service.GetReportOperation;
import com.google.auto.service.AutoService;
import software.amazon.smithy.java.aws.integrations.lambda.SmithyServiceProvider;
import software.amazon.smithy.java.server.RequestContext;
import software.amazon.smithy.java.server.Service;

@AutoService(SmithyServiceProvider.class)
public final class SmithyApp implements SmithyServiceProvider {

    private static final Service SERVICE;

    static {
        // This is statically initialized such that Lambda can re-use it across invocations
        SERVICE = Animals4LiveService.builder()
                .addCreateReportOperation(new CreateReportImpl())
                .addGetReportOperation(new GetReportImpl())
                .build();
    }

    @Override
    public Service get() {
        return SERVICE;
    }

    private static final class CreateReportImpl implements CreateReportOperation {

        @Override
        public CreateReportOutput createReport(CreateReportInput input, RequestContext context) {
            return CreateReportOutput.builder()
                    .reportId("ID")
                    .bodyText("text")
                    .title("title!!!")
                    .build();
        }
    }

    private static final class GetReportImpl implements GetReportOperation {

        @Override
        public GetReportOutput getReport(GetReportInput input, RequestContext context) {
            return GetReportOutput.builder()
                    .reportId("Get 1")
                    .bodyText("From Get")
                    .title("From GET TITLE")
                    .build();
        }
    }
}
