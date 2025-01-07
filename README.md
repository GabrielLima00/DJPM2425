# Task Manager App

Bem-vindo ao repositório do **Task Manager App**!  
Esta aplicação foi desenvolvida no âmbito da disciplina de **Desenvolvimento de Jogos para Plataformas Móveis** e tem como objetivo facilitar a gestão de tarefas diárias através de uma interface intuitiva e responsiva para dispositivos móveis Android.

---

## Índice
- [Descrição do Projeto](#descrição-do-projeto)  
- [Tecnologias Utilizadas](#tecnologias-utilizadas)  
- [Funcionalidades](#funcionalidades)  
- [Arquitetura do Projeto](#arquitetura-do-projeto)  
- [Funções e Componentes Principais](#funções-e-componentes-principais)  
- [Fluxo de Navegação](#fluxo-de-navegação)  
- [Gestão de Notificações](#gestão-de-notificações)  
- [Segurança e Autenticação](#segurança-e-autenticação)  
- [Desafios e Soluções Técnicas](#desafios-e-soluções-técnicas)  
- [Capturas de Ecrã](#capturas-de-ecrã)  

---

## Descrição do Projeto
O **Task Manager App** é uma aplicação Android desenvolvida em **Kotlin** com recurso ao **Jetpack Compose** para a interface gráfica.  
Permite gerir tarefas, agendar compromissos e visualizar eventos num calendário. A aplicação suporta múltiplos ecrãs, tais como:  
- Login/Registo  
- Visualização de Calendário  
- Criação e Edição de Tarefas  
- Lembretes com Notificações  

---

## Tecnologias Utilizadas
- **Linguagem de Programação:** Kotlin  
- **Framework UI:** Jetpack Compose  
- **Arquitetura:** MVVM (Model-View-ViewModel)  
- **Notificações:** WorkManager  
- **Navegação:** Navigation Component  

---

## Funcionalidades
- Calendário Interativo – Visualização mensal e diária.  
- Gestão de Tarefas – Criar, editar e eliminar tarefas.  
- Autenticação – Login e registo de utilizadores.  
- Notificações de Tarefas – Notificações automáticas para lembrar o utilizador de tarefas agendadas.  
- Sincronização de Dados – Armazenamento de dados local com possibilidade de sincronização futura.  

---

## Arquitetura do Projeto
O projeto segue a arquitetura **MVVM (Model-View-ViewModel)**, garantindo uma separação clara entre a lógica de negócio, a interface do utilizador e a gestão de dados.

- **Model (Modelo de Dados):**  
  Representação das entidades como `Task.kt`, que define as tarefas com campos de título, descrição e data.  

- **ViewModel:**  
  - `AddTaskViewModel.kt` – Controla a lógica de adição e edição de tarefas.  
  - `LoginViewModel.kt` – Gere a autenticação e comunica com o repositório de utilizadores.  
  - `SignUpViewModel.kt` – Lida com o registo de novos utilizadores.  

- **Repository:**  
  - `AuthRepository.kt` – Responsável pela lógica de autenticação.  
  - `TaskRepository.kt` – Faz a gestão de tarefas, permitindo adicionar, editar e eliminar.  

- **UI (Interface de Utilizador):**  
  - `CalendarScreen.kt` – Apresenta um calendário interativo com as tarefas registadas.  
  - `AddTaskView.kt` – Formulário para criar ou editar tarefas.  
  - `LoginScreen.kt` – Ecrã de login e registo.  

---

## Funções e Componentes Principais
### MainActivity.kt
- `onCreate` – Inicializa a interface e gere a navegação principal.  
- `TaskManagerApp` – Define a navegação entre diferentes ecrãs, usando o `NavHost`.  

### Navegação
- `NavHost` em `TaskManagerApp` – Centraliza a navegação entre `LoginScreen`, `CalendarScreen`, `AddTaskView`.  

### TaskRepository.kt
- `getTasks` – Retorna uma lista de tarefas do utilizador.  
- `addTask` – Adiciona uma nova tarefa.  
- `deleteTask` – Elimina uma tarefa com base no ID.  

---

## Fluxo de Navegação
O fluxo de navegação da aplicação está estruturado para proporcionar uma experiência fluida e intuitiva:

1. **Ecrã de Login** – O utilizador inicia sessão ou regista-se.  
2. **Calendário (Ecrã Principal)** – Após o login, é apresentado o calendário com as tarefas.  
3. **Adição/Edição de Tarefas** – A partir do ecrã principal, o utilizador pode adicionar ou editar tarefas.  
4. **Notificações** – As tarefas agendadas enviam lembretes automáticos.  

---

## Gestão de Notificações
### NotificationWorker.kt
- `doWork` – Função responsável por enviar notificações de lembrete. Esta função é invocada pelo `WorkManager`, garantindo que as notificações sejam disparadas mesmo quando a aplicação está em segundo plano.  
- **Configuração:**  
  As notificações são configuradas através do `TaskNotificationManager.kt`, que define a aparência e o comportamento do alerta.  

---

## Segurança e Autenticação
- **Registo e Login:**  
  - `LoginViewModel` e `SignUpViewModel` comunicam com o `AuthRepository` para validar credenciais.  
  - **Validação Básica:** As passwords e emails são verificados localmente antes de serem enviados para o repositório de autenticação.  
  - **Persistência:** Sessões podem ser guardadas localmente para manter o utilizador autenticado.  

---

## Desafios e Soluções Técnicas
1. **Notificações em Segundo Plano**  
   **Solução:** Implementação do WorkManager para execução periódica e envio de lembretes.  

2. **Persistência de Tarefas**  
   **Solução:** Utilização do Room Database para armazenamento persistente.  

3. **Navegação Dinâmica**  
   **Solução:** Navigation Component para centralizar e gerir a navegação sem estados conflitantes.  

---
## Capturas de Ecrã
### Ecrã de Login
<img src="https://github.com/user-attachments/assets/240307ce-dd76-4a0e-b5ec-26e5d5c21a18" alt="imagem_2025-01-07_183111576" width="200"/>

### Ecrã de Registo
<img src="https://github.com/user-attachments/assets/2bcee093-9dca-4e2d-bed0-e9f146777caf" alt="Captura de ecrã 2025-01-07 183121" width="200"/>

### Adicionar Tarefa
<img src="https://github.com/user-attachments/assets/a9f3bc83-9ff6-4fda-9655-a28287748702" alt="imagem_2025-01-07_183339172" width="200"/>

### Calendário de Tarefas
<img src="https://github.com/user-attachments/assets/0bb5d8a9-0268-4ff1-9e44-8bbf419d6a72" alt="imagem_2025-01-07_183355001" width="200"/>

### Seletor de Data
<img src="https://github.com/user-attachments/assets/757e3d6a-2d6f-4358-9584-630acf13ec3f" alt="imagem_2025-01-07_183408690" width="200"/>

