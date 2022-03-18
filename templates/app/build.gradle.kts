
dependencies {
    {% if inputs.metrics_tool == 'Prometheus' %}
    implementation("{{computed_inputs.dependency}}")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    {% elif inputs.metrics_tool == 'AWS CloudWatch' %}
    implementation("com.stackspot.springboot:cloudwatch-starter:0.0.1")
    runtimeOnly("software.amazon.awssdk:sts:2.17.143")
    {% endif %}
}
