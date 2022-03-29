## **Implementação**

### **Inputs**

Os inputs necessários para utilizar o plugin são:
| **Campo**    | **Valor**                | **Parâmetro**                    | **Descrição**                                                                 |
| :------------| :------------------------| :--------------------------------| :-----------------------------------------------------------------------------|
| metrics_tool | Padrão: "AWS CloudWatch" | ["AWS CloudWatch", "Prometheus"] | Identificador de definição para qual ferramenta as métricas devem ser enviadas|


## Definindo o nome da métrica

Para definir o nome da métrica, siga a convenção definida na [documentação do prometheus](https://prometheus.io/docs/practices/naming/#metric-names) e Spring Boot [actuator-metrics-customizing](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics.customizing). 

## Criando métricas de negócio para sua aplicação

Para incluir suas próprias métricas, injete a dependência do [MeterRegistry](http://micrometer.io/docs/concepts#_registry), que é a interface responsável por registrar as métricas de sua aplicação. Assim, você já terá a capacidade de criar suas próprias métricas customizadas.

Agora, basta escolher qual será o tipo da sua métrica. Nossa stack disponibiliza a criação dos seguintes tipos:

- [Counter](#counter)
- [Gauge](#gauge)
- [Summary](#summary)
- [Timer](#timer)

### Counter

*Counter* é a interface de criação para métricas do tipo "contador". A interface *Counter* permite que uma métrica seja incrementada por um valor fixo, que deve ser positivo.
Um contador deve ser utilizado quando você precisa saber o valor absoluto de alguma coisa, como o número de novos clientes inseridos, a quantidade de logins, etc.


> Dica: Não utilize o contador em alguma métrica na qual você possa medir o tempo com o ***[Timer](#timer)*** ou sumarizar com o ***[Summary](#summary)***. Tanto o ***Timer*** quanto o ***Summary*** sempre publicam a contagem absoluta dos eventos em adição às demais medições.

- [javadoc](https://www.javadoc.io/doc/io.micrometer/micrometer-core/1.5.6/io/micrometer/core/instrument/Counter.html)
- [referência micrometer](http://micrometer.io/docs/concepts#_counters)

### Gauge

***Gauge*** é a interface de criação para métricas do tipo medida instantânea. Ela é usada para obter o valor atual de uma definição. Por exemplo, podemos utilizar o ***Gauge*** para mostrar a quantidade de tarefas em execução ou tamanho de uma fila de processos.

> Dica: Os medidores são úteis para monitorar métricas em que os valores possam subir e descer. Por isso, nunca utilize o ***Gauge*** para monitorar alguma coisa que seja apenas crescente. Nesse caso, utilize o *[Counter](#counter)*.

- [javadoc](https://www.javadoc.io/doc/io.micrometer/micrometer-core/1.5.6/io/micrometer/core/instrument/Gauge.html)
- [referência micrometer](http://micrometer.io/docs/concepts#_gauges)

### Timer

Métricas do tipo ***Timer*** são utilizadas para registro de tempo de resposta e quantidade de eventos. São comumente utilizadas para registro de latência.

- [javadoc](https://www.javadoc.io/doc/io.micrometer/micrometer-core/1.5.6/io/micrometer/core/instrument/Timer.html)
- [referência micrometer](http://micrometer.io/docs/concepts#_timers)

### Summary

Uma métrica do tipo **Summary** é utilizada para rastrear eventos distribuídos. Estruturalmente, é similar ao *[Timer](#timer)*, mas seus valores gravados não representam uma unidade de tempo.
Basicamente, o sumário entrega um contador, a soma dos registros e o valor máximo de um valor registrado.

- [javadoc](https://www.javadoc.io/doc/io.micrometer/micrometer-core/1.5.6/io/micrometer/core/instrument/DistributionSummary.html)
- [referência micrometer](http://micrometer.io/docs/concepts#_distribution_summaries)
