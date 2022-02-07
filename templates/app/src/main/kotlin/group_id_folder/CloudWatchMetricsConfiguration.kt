{% if inputs.metrics_tool == 'AWS CloudWatch' %}
package {{project_group_id}}

import io.micrometer.cloudwatch2.CloudWatchConfig
import io.micrometer.cloudwatch2.CloudWatchMeterRegistry
import io.micrometer.core.instrument.Clock
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient

@Configuration
@EnableConfigurationProperties(CloudWatchMetricsProperties::class)
class CloudWatchMetricsConfiguration(private val cloudWatchMetricsProperties: CloudWatchMetricsProperties) {
    @Bean
    fun cloudWatchConfig() = object : CloudWatchConfig {
        override fun get(key: String): String? = null

        override fun namespace(): String = cloudWatchMetricsProperties.namespace
    }

    @Bean
    fun meterRegistry(cloudWatchConfig: CloudWatchConfig) = CloudWatchMeterRegistry(
        cloudWatchConfig,
        Clock.SYSTEM,
        cloudWatchMetricsProperties.endpoint?.let { CloudWatchAsyncClient.builder().endpointOverride(it).build() }
            ?: CloudWatchAsyncClient.create()
    )
}
{% endif %}
