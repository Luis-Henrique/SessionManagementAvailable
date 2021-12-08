# SessionManagementAvailable

#Descrição
Gerenciamento de votos de cooperados para sessões em pauta.

#Desenvolvimento
JDK 11: necessário para executar o projeto
Eclipse: ferramenta para desenvolvimento do projeto
Maven: para realizar o build do projeto
Para iniciar o desenvolvimento é necessário clonar o projeto em algum diretório local com o comando abaixo.
git clone https://github.com/Luis-Henrique/SessionManagementAvailable

#Construção(Build)
Eclipse: selecionar o comando maven clean
Eclipse: selecionar o comando maven build

#Deploy
https://sessionmanagement.herokuapp.com/

#Versionamento
MAJOR. MINOR MAINTENANCE.BUILD. Exemplo: 1.0.1.0.
- MAJOR: Versão principal e deve ser incrementada sempre que uma grande mudança
estrutural que impacte quem a consome ou quando há uma quebra de compatibilidade.
- MINOR: Incrementada sempre que novas funcionalidades forem incluídas.
- MAINTENANCE: Incrementada quando houve correções.
- BUILD: Incrementada sempre que um novo build for realizado.

Esse projeto não apresenta versionamento no momento.

#Testes
 Pré Requisitos 
    - Aplicativo Postman. Link para download: https://www.postman.com/downloads/
    - Acesso a internet.
    - URL de acesso: https://sessionmanagement.herokuapp.com/
    
 Funcionalidades
    - Cadastro de Convocação
    - Cadastro de Sessão em Pauta
    - Cadastro de Votos
    - Consulta da Situação das Sessões
    
 Sequência de Testes
    - Cadastro de Convocação 
        Pré requisito: N/A
        Método: POST 
        URL: https://sessionmanagement.herokuapp.com/convocation
        Corpo da mensagem: {“title”: “XXX”}
        Resultado esperado: 200 OK com o id do objeto criado no headers
        Observações: N/A
        
    - Cadastro de Sessão 
        Pré requisito: convocação cadastrada
        Método: POST 
        URL: https://sessionmanagement.herokuapp.com/session/{id_convocacao}?time=X
        Corpo da mensagem: Nenhum
        Resultado esperado: 200 OK com o id do objeto criado no headers
        Observações: Informar o tempo que a sessão ficará aberta. EX. Para 4 minutos “?time=4”. Caso não informado ficará 1 minuto por default.
        
    - Cadastro de Votos 
        Pré requisito: sessão cadastrada
        Método: POST 
        URL: https://sessionmanagement.herokuapp.com/vote/{id_sessao}
        Corpo da mensagem: {“cpf”:“xxxxxxxxxxx”, “voteValue” : “x”}
        Resultado esperado: 200 OK com o id do objeto criado no headers
        Observações: Valores dos votos: 1 – SIM, 2 – NÂO. Para realizar voto é necessário infomar um CPF válido 
       
    - Consulta o Status de Todas as Sessões 
        Pré requisito: N/A
        Método: GET 
        URL: https://sessionmanagement.herokuapp.com/session
        Corpo da mensagem: Nenhum
        Resultado esperado: 200 OK. Deverá retornar um json com as sessões cadastradas
        Observações: 
        
    - Consulta o Status da Sessão por ID 
        Pré requisito: N/A
        Método: GET 
        URL: https://sessionmanagement.herokuapp.com/session/{id_sessao}
        Corpo da mensagem: Nenhum
        Resultado esperado: 200 OK. Deverá retornar um json com a sessão informada
        Observações: 




