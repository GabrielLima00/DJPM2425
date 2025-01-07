# 📝 Task Manager App

Bem-vindo ao repositório do **Task Manager App**!  
Esta aplicação foi desenvolvida durante a disciplina de **Desenvolvimento de Jogos para Plataformas Móveis** e tem como objetivo facilitar a gestão de tarefas diárias através de uma interface intuitiva e responsiva para dispositivos móveis Android.

---

## 📂 Índice
- [Descrição do Projeto](#-descrição-do-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Funções e Componentes Principais](#-funções-e-componentes-principais)
- [Gestão de Notificações](#-gestão-de-notificações)
- [Segurança e Autenticação](#-segurança-e-autenticação)
- [Desafios e Soluções Técnicas](#-desafios-e-soluções-técnicas)
- [Capturas de Ecrã](#-capturas-de-ecrã)

---

## 📜 Descrição do Projeto
O **Task Manager App** é uma aplicação Android construída em **Kotlin** com recurso a **Jetpack Compose** para a interface gráfica.  
Permite gerir tarefas, agendar compromissos e visualizar eventos num calendário. A aplicação suporta múltiplos ecrãs como:
- Login/Registo
- Visualização de Calendário
- Criação e Edição de Tarefas
- Lembretes com Notificações

---

## 🛠️ Tecnologias Utilizadas
- **Linguagem de Programação:** Kotlin  
- **Framework UI:** Jetpack Compose  
- **Arquitetura:** MVVM (Model-View-ViewModel)  
- **Notificações:** WorkManager  
- **Navegação:** Navigation Component  

---

## ✨ Funcionalidades
- 🗓️ **Calendário Interativo** – Visualização mensal e diária.  
- ✅ **Gestão de Tarefas** – Criar, editar e eliminar tarefas.  
- 🔐 **Autenticação** – Login e Registo de utilizadores.  
- 🔔 **Notificações de Tarefas** – Notificações automáticas para lembrar o utilizador de tarefas agendadas.  
- 🔄 **Sincronização de Dados** – Armazenamento de dados local com possibilidade de sincronização futura.  

---

## 🏗️ Arquitetura do Projeto
O projeto segue a arquitetura **MVVM (Model-View-ViewModel)**, garantindo separação clara entre a lógica de negócio, interface do utilizador e gestão de dados.

- **Model (Modelo de Dados):**  
  Representação das entidades como `Task.kt`, que define as tarefas com campos de título, descrição, e data.  

- **ViewModel:**  
  - `AddTaskViewModel.kt` – Controla a lógica de adição e edição de tarefas.  
  - `LoginViewModel.kt` – Gere a autenticação e comunicação com o repositório de utilizadores.  
  - `SignUpViewModel.kt` – Lida com o registo de novos utilizadores.  

- **Repository:**  
  - `AuthRepository.kt` – Responsável pela lógica de autenticação.  
  - `TaskRepository.kt` – Faz a gestão de tarefas, permitindo adicionar, editar e eliminar.  

- **UI (Interface de Utilizador):**  
  - `CalendarScreen.kt` – Apresenta um calendário interativo com as tarefas registadas.  
  - `AddTaskView.kt` – Formulário para criar ou editar tarefas.  
  - `LoginScreen.kt` – Ecrã de login e registo.  

---

## 🔧 Funções e Componentes Principais
### MainActivity.kt
- **`onCreate`** – Inicializa a interface e gere a navegação principal.  
- **`TaskManagerApp`** – Define a navegação entre diferentes ecrãs, usando o `NavHost`.  

### Navegação
- **`NavHost` em `TaskManagerApp`** – Centraliza a navegação entre `LoginScreen`, `CalendarScreen`, `AddTaskView`.  

### TaskRepository.kt
- **`getTasks`** – Retorna uma lista de tarefas do utilizador.  
- **`addTask`** – Adiciona uma nova tarefa.  
- **`deleteTask`** – Elimina uma tarefa com base no ID.  

---

## 🔔 Gestão de Notificações
### NotificationWorker.kt
- **`doWork`** – Função responsável por enviar notificações de lembrete. Esta função é invocada pelo `WorkManager`, que assegura que as notificações sejam disparadas mesmo quando a aplicação está em segundo plano.  
- **Configuração:**  
  As notificações são configuradas através do `TaskNotificationManager.kt`, que define a aparência e o comportamento do alerta.  

---

## 🔐 Segurança e Autenticação
- **Registo e Login:**  
  - `LoginViewModel` e `SignUpViewModel` comunicam com o `AuthRepository` para validar credenciais.  
  - **Validação Básica:** As passwords e emails são verificados localmente antes de serem enviados para o repositório de autenticação.  
  - **Persistência:** Sessões podem ser guardadas localmente para manter o utilizador autenticado.  

---

## 🧩 Desafios e Soluções Técnicas
### 1. Implementação de Notificações em Segundo Plano  
**Desafio:** As notificações não estavam a ser disparadas quando a aplicação estava fechada.  
**Solução:** A integração do **WorkManager** garantiu que as notificações fossem enviadas de forma periódica e confiável, mesmo quando a aplicação não estava em execução ativa.

### 2. Sincronização de Dados  
**Desafio:** Implementar uma forma eficiente de guardar tarefas localmente sem comprometer o desempenho.  
**Solução:** Utilização do **Room Database** para persistência local, garantindo performance e segurança.

### 3. Navegação Complexa  
**Desafio:** Gerir a navegação entre múltiplos ecrãs sem sobrecarregar o `MainActivity`.  
**Solução:** Uso do **Navigation Component** para centralizar e organizar a navegação, garantindo uma experiência de utilizador fluida.

---

## 📸 Capturas de Ecrã
Adiciona aqui algumas capturas de ecrã do funcionamento da aplicação.  
Exemplo:  
![Login Screen](./screenshots/login_screen.png)  
![Calendar View](./screenshots/calendar_view.png)  
