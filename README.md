# watch-directory-dat

Projeto desenvolvido para ler arquivos .dat
Foi utilizado o Watch Service, que é uma API de baixo nível, estável e que encontra-se dentro do próprio JSE.

Para mais informações, segue o link.
https://docs.oracle.com/javase/tutorial/essential/io/notification.html

## Arquivos
Os tipos de arquivos que devem ser lidos parte do seguinte princípio, encontrados na pasta data/in/*.dat
```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
```

e o arquivo de saída .dat que deve ser encontrado em data/out/ tem o seguinte formato: 
```
Quantidade de clientes no arquivo de entrada: 2
Quantidade de vendedor no arquivo de entrada: 2
ID da venda mais cara: 10
O pior vendedor: Paulo
```
