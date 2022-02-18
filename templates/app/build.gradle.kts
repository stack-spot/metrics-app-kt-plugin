
dependencies {
    {% if inputs.metrics_tool == 'Prometheus' %}
    implementation("{{computed_inputs.dependency}}")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    {% elif inputs.metrics_tool == 'AWS CloudWatch' %}
    implementation("com.stackspot.springboot:cloudwatch-starter:0.0.1")
    {% endif %}
}

{% if inputs.metrics_tool == 'AWS CloudWatch' %}
application {
    applicationDefaultJvmArgs += "-Dstackspot.metrics.cloudwatch.endpoint=http://localhost:4566"
}
{% endif %}
