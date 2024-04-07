#Material de Apoio
#Introdução

A Arquitetura MVC (Model-View-Controller) é um padrão amplamente adotado no desenvolvimento de software, especialmente em aplicações web e móveis. Surgindo inicialmente na década de 1970 como parte do framework Smalltalk-80, desenvolvido por Trygve Reenskaug na Xerox PARC, o MVC revolucionou como as aplicações são projetadas e organizadas. Seu propósito fundamental é separar as preocupações numa aplicação, dividindo-a em três componentes principais: Modelo (Model), Visão (View) e Controlador (Controller). Essa separação permite uma estrutura mais organizada, reutilização de código e facilita a manutenção e evolução das aplicações ao longo do tempo.

Neste trabalho, exploraremos em detalhes o funcionamento da arquitetura MVC, sua origem, propósito, os problemas que ela resolve e os desafios que ainda persistem nesse modelo. 


#Surgimento e propósito

A arquitetura MVC foi concebida na década de 1970 por Trygve Reenskaug, um pesquisador da Xerox PARC, como parte do desenvolvimento do framework Smalltalk-80. O propósito principal do MVC era separar as preocupações relacionadas aos dados, à lógica de negócios e à interface do usuário, facilitando a manutenção e evolução das aplicações.


#Problemas resolvidos
- Separação de preocupações: A arquitetura MVC resolve o problema da mistura de código de apresentação, lógica de negócios e acesso a dados em uma única camada, permitindo uma estrutura mais organizada e modular.

- Reutilização de código: ao separar as camadas, o código pode ser reutilizado mais facilmente em diferentes partes da aplicação, melhorando a eficiência do desenvolvimento.

- Facilidade de manutenção: com a divisão clara das responsabilidades entre Modelo, Visão e Controlador, as mudanças em uma parte da aplicação tendem a ter um impacto limitado nas outras partes, facilitando a manutenção e evolução do sistema.


#Problemas existentes
- Complexidade em aplicações grandes: em aplicações de grande escala, a separação estrita entre Modelo, Visão e Controlador pode resultar em complexidade adicional devido à necessidade de coordenar a interação entre essas camadas.

- Tendência à duplicação de código: em algumas implementações, pode haver uma tendência à duplicação de código, especialmente na camada de Controlador, se não houver uma gestão cuidadosa das responsabilidades.

- Sincronização entre camadas: em cenários onde há uma grande interdependência entre o Modelo, a Visão e o Controlador, garantir uma sincronização adequada pode ser desafiador e levar a problemas de consistência.

#Funcionamento da Arquitetura
Funcionamento da Arquitetura MVC (Model-View-Controller) é baseado na separação de responsabilidades entre três componentes principais: Modelo (Model), Visão (View) e Controlador (Controller). Cada um desses componentes desempenha um papel específico na aplicação, facilitando a organização, a manutenção e a evolução do software.

#Modelo (Model):
O Modelo representa os dados da aplicação, incluindo a lógica de negócios e as regras de acesso aos dados.
Ele é responsável por gerenciar o estado da aplicação, interagindo com o banco de dados para recuperar e armazenar informações.
Além disso, o Modelo pode conter operações relacionadas à validação dos dados e ao processamento das regras de negócios.

#Visão (View):
A Visão é responsável pela apresentação dos dados ao usuário final.
Ela exibe as informações do Modelo de forma visualmente atraente e interativa, utilizando elementos de interface gráfica como HTML, CSS e JavaScript em aplicações web.
A Visão não deve conter lógica de negócios complexa; seu foco principal é exibir os dados de forma clara e compreensível para o usuário.

#Controlador (Controller):
O Controlador atua como intermediário entre o Modelo e a Visão.
Ele recebe as interações do usuário na interface gráfica (View) e as traduz em ações que afetam o Modelo ou a própria Visão.
O Controlador processa as requisições do usuário, atualiza o estado do Modelo conforme necessário e atualiza a Visão para refletir essas alterações.


#Fluxo de funcionamento:
O usuário interage com a interface gráfica (View), como clicando em um botão ou preenchendo um formulário.
A interação do usuário é capturada pelo Controlador, que decide como lidar com essa interação.
O Controlador pode fazer chamadas ao Modelo para atualizar dados ou obter informações necessárias para responder à interação do usuário.
Após as alterações no Modelo, o Controlador atualiza a Visão para refletir as mudanças aos olhos do usuário.
O ciclo continua conforme o usuário interage com a aplicação, mantendo a separação clara entre Modelo, Visão e Controlador.
Essa separação de responsabilidades permite uma estrutura modular e facilita a manutenção e evolução da aplicação, pois mudanças em uma camada geralmente têm impacto limitado nas outras camadas.
