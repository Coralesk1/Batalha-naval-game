Battle Chip - Batalha Naval
Projeto desenvolvido para a disciplina de Programação Avançada do curso de Ciência da Computação na Universidade Tuiuti do Paraná (UTP).


Pré-requisitos Técnicos
    - JDK: Java 21 ou superior.
    - IDE Recomendada: IntelliJ IDEA ou VS Code com extensões Java.

Como Executar
    O projeto está organizado em pacotes. Siga os passos abaixo para rodar:

    - Via IDE (IntelliJ, VS Code ou Eclipse):

        - Abra a pasta raiz do projeto.

        - Localize o arquivo Main.java na raiz e execute-o.

    - Via Terminal:

        - Navegue até a pasta do projeto.

        - Compile o arquivo principal: javac Main.java

        - Execute o programa: java Main

- Modos de Jogo
    1 vs 1: Partida local para dois jogadores humanos.

    1 vs PC: Partida contra a Inteligência Artificial. A IA possui um sistema de memória para não repetir tiros em coordenadas já atacadas.

- Organização do Projeto
   O código está dividido nas seguintes estruturas:

    Raiz: Contém as classes principais de controle (Main, Jogo, Jogador, Tabuleiro).

    Pacote Navios: Contém a classe base Navio e as especializações (Submarino, Destroyer, Cruzador, Encouraçado e Porta-Aviões).

    Pacote Utils: Classes de suporte para limpeza de console e validações de dados.

    Pacote Regras: Instruções detalhadas de como jogar.

- Documentação Adicional
    Para detalhes sobre as decisões de projeto, uso de referências de memória e conceitos de Orientação a Objetos aplicados, consulte o Relatório Técnico incluído no arquivo de entrega.