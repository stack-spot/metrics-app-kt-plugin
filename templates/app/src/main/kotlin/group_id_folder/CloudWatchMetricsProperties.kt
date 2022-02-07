{% if inputs.metrics_tool == 'AWS CloudWatch' %}
package {{project_group_id}}

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.net.URI

@ConstructorBinding
@ConfigurationProperties("stackspot.metrics.cloudwatch")
data class CloudWatchMetricsProperties(val endpoint: URI? = null, val namespace: String)
{% endif %}
