## Como o plugin funciona

- O framework de métricas usado aqui é o [micrometer](https://micrometer.io/).

#### **Utilizando CloudWatch**

Para que o CloudWatch consiga receber as métricas da aplicação este plugin entrega todas as configurações necessárias:
- O plugin também configura via cdk a infra da aplicação, adicionando a política de métricas do CloudWatch, para mais informações acesse [Stackspot-CloudWatchMetricsPolicy](https://github.com/stack-spot/metrics-app-kt-plugin/blob/main/templates/infra/src/main/kotlin/group_id_folder/CloudWatchMetricsPolicy.kt)
- Adiciona a dependência do `micrometer` para `cloudwatch2`.
- No `docker-compose` adiciona um contêiner do Localhost vinculando o mesmo no contêiner do Prometheus.
- Entrega configurações de Beans do `meterRegistry` e `cloudWatchConfig`.

> Para usar o **AWS CloudWatch** é necessário configurar as credenciais da **AWS** como variáveis de ambiente.

#### **Utilizando Prometheus**

Para que o Prometheus consiga coletar as métricas da aplicação, este plugin entrega todas as configurações necessárias: 
- Adiciona a dependência do `micrometer`.
- No `docker-compose` adiciona um contêiner do Prometheus.
- Cria um arquivo **prometheus.yml**, onde será definido um http endpoint para coletar as métricas da aplicação, que podem ser visualizadas na path `/actuator/prometheus`.
