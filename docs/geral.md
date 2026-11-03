> Por que usar?

Desacoplamento, escalabilidade, persistência, pontos de tráfego, processamento em lote, ordenação (FIFO), entrega.

## Tipos de exchange

Mensagem tem uma rounting key no cabeçalho (chave de roteamento), a exchange tem um tipo definido e com base na routing key vai enviar pra fila correspondente.

- **Direto**: o exchange envia direto pra fila que tem o *routing key correspondente*. 
- **Fanout**: *toda fila* que fizer ligação com a exchange recebe a mesma mensagem.
- **Topic**: queue.* recebe *independente da routing key*
- **Headers**: com base em argumentos adicinados nos *cabeçalhos*, semelhante ao de topico mas ao invés de routing key vai por cabeçalho