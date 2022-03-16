{% if inputs.metrics_tool == 'AWS CloudWatch' %}
package {{project_group_id}}

import software.amazon.awscdk.services.iam.Effect
import software.amazon.awscdk.services.iam.Policy
import software.amazon.awscdk.services.iam.PolicyStatement
import software.constructs.Construct

class CloudWatchMetricsPolicy(scope: Construct, id: String): Policy(scope, id) {
    companion object {
        @JvmStatic
        private val actions = listOf(
            "cloudwatch:PutMetricStream",
            "cloudwatch:PutMetricAlarm",
            "cloudwatch:PutMetricData",
            "cloudwatch:StopMetricStreams",
            "cloudwatch:GetMetricData",
            "cloudwatch:DescribeAlarmsForMetric",
            "cloudwatch:DeleteMetricStream",
            "cloudwatch:GetMetricStream",
            "cloudwatch:GetMetricStatistics",
            "cloudwatch:GetMetricWidgetImage",
            "cloudwatch:ListMetrics",
            "cloudwatch:StartMetricStreams"
        )
    }

    init {
        this.addStatements(
            PolicyStatement.Builder.create()
                .actions(actions)
                .effect(Effect.ALLOW)
                .resources(listOf("*"))
                .build()
        )
    }
}
{% endif %}
