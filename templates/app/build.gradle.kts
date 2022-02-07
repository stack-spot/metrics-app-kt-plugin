
dependencies {
    {% if inputs.metrics_tool == 'Prometheus' %}
    implementation("{{computed_inputs.dependency}}")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    {% elif inputs.metrics_tool == 'AWS CloudWatch' %}
    implementation("io.micrometer:micrometer-registry-cloudwatch2")
    runtimeOnly(kotlin("reflect"))
    {% endif %}
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

{% if inputs.metrics_tool == 'AWS CloudWatch' %}
application {
    applicationDefaultJvmArgs += "-Dstackspot.metrics.cloudwatch.endpoint=http://localhost:4566"
}
{% endif %}
