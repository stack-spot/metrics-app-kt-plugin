# **Metrics Kotlin Plugin** 

- **Descrição:** O plugin de `metrics` visa padronizar as métricas geradas pela aplicação.
- **Categoria:** Observability. 
- **Stack:** zup-kotlin-stack.
- **Criado em:** 08/02/2022.
- **Última atualização:** 08/02/2022.
- **Download:** https://github.com/stack-spot/metrics-app-kt-plugin.git

## **Visão Geral**

O **metrics-app-kt-plugin** é um plugin que tem como objetivo padronizar as métricas geradas pelas aplicações. Ao adicionar o plugin na aplicação é possível escolher se as métricas serão enviadas para o [AWS CloudWatch](https://aws.amazon.com/pt/cloudwatch/) ou para o [Prometheus](https://prometheus.io/docs/introduction/overview/).

### **Pré-requisitos**
- Para utilizar esse plugin, é necessário:
  -  Instalação do STK CLI da StackSpot.
  -  Importar a stack `zup-kotlin-stack` através do STK CLI.
     - `stk import https://github.com/stack-spot/zup-kotlin-stack`   
  - Ter uma aplicação criada pelo tamplate da stack importada.
- Possuir Java 11 instalado na máquina.

### **Como o plugin de métricas funciona?** 

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

### **Como adicionar o plugin na aplicação?**

Para aplicar o plugin, primeiramente é necessário ter executado os passos do [Pré-requisitos](#Pré-requisitos). Após isso, crie sua a aplicação utilizando o CLI da StackSpot, acesse a pasta raiz do projeto e execute o comando abaixo para aplicar o plugin:
 
```
stk apply plugin zup-kotlin-stack/metrics-app-kt-plugin
```


## **Configuração**

### **Inputs**

Os inputs necessários para utilizar o plugin são:
| **Campo**    | **Valor**                | **Parâmetro**                    | **Descrição**                                                                 |
| :------------| :------------------------| :--------------------------------| :-----------------------------------------------------------------------------|
| metrics_tool | Padrão: "AWS CloudWatch" | ["AWS CloudWatch", "Prometheus"] | Identificador de definição para qual ferramenta as métricas devem ser enviadas|
