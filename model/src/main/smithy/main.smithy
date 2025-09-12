$version: "2"

namespace com.animals4live

use aws.protocols#restJson1

@restJson1
service Animals4LiveService {
    version: "2025-01-01"
    resources: [
        Report
    ]
}

resource Report {
    identifiers: {
        reportId: ReportId
    }
    properties: {
        title: String
        bodyText: String
    }
    create: CreateReport
    read: GetReport
}

@readonly
@http(method: "GET", uri: "/app/report/{reportId}")
operation GetReport {
    input := for Report {
        @httpLabel
        @required
        $reportId
    }

    output := for Report {
        @required
        $reportId

        @required
        $title

        @required
        $bodyText
    }
}

@http(method: "POST", uri: "/app/report")
operation CreateReport {
    input := for Report {
        @required
        $title

        @required
        $bodyText
    }

    output := for Report {
        @required
        $reportId

        @required
        $title

        @required
        $bodyText
    }
}

string ReportId

structure CreateReportInput {
    @required
    title: String

    @required
    bodyText: String
}
