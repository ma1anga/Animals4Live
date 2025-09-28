$version: "2"

namespace com.animals4live

use aws.protocols#awsJson1_1

@awsJson1_1
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
operation GetReport {
    input := for Report {
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
