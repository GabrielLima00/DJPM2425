# 📝 Task Manager App

Bem-vindo ao repositório do **Task Manager App**!  
Esta aplicação foi desenvolvida durante a disciplina de **Desenvolvimento de Jogos para Plataformas Móveis** e tem como objetivo facilitar a gestão de tarefas diárias através de uma interface intuitiva e responsiva para dispositivos móveis Android.

---

## 📂 Índice
- [Descrição do Projeto](#-descrição-do-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Como Correr o Projeto](#-como-correr-o-projeto)
- [Capturas de Ecrã](#-capturas-de-ecrã)
- [Futuras Implementações](#-futuras-implementações)
- [Contribuições](#-contribuições)
- [Autores](#-autores)
- [Licença](#-licença)

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
- **Outras Ferramentas:** Android Studio, Navigation Component, WorkManager (para notificações)  

---

## ✨ Funcionalidades
- 🗓️ **Calendário Interativo** – Visualização mensal e diária.  
- ✅ **Gestão de Tarefas** – Criar, editar e eliminar tarefas.  
- 🔐 **Autenticação** – Login e Registo de utilizadores.  
- 🔔 **Notificações de Tarefas** – Notificações automáticas para lembrar o utilizador de tarefas agendadas.  
- 🔄 **Sincronização de Dados** – Armazenamento de dados local com possibilidade de sincronização futura.  

---

## 🏗️ Arquitetura do Projeto
O projeto segue a arquitetura **MVVM (Model-View-ViewModel)**, que separa a lógica de interface da lógica de negócio, garantindo uma aplicação modular e fácil de manter.

- **Modelos (Models):**  
  Representação das entidades principais, como o modelo `Task.kt`, que define a estrutura de uma tarefa.  

- **ViewModels:**  
  - `AddTaskViewModel.kt` – Lógica de criação e edição de tarefas.  
  - `LoginViewModel.kt` – Gestão do processo de login e autenticação.  
  - `SignUpViewModel.kt` – Lida com o registo de novos utilizadores.  

- **Repositórios (Repositories):**  
  - `AuthRepository.kt` – Lógica de autenticação e comunicação com bases de dados ou serviços.  
  - `TaskRepository.kt` – Gestão das tarefas, desde a criação até à eliminação.  

- **UI (Interface de Utilizador):**  
  - `CalendarScreen.kt` – Ecrã de calendário para visualizar tarefas.  
  - `AddTaskView.kt` – Interface para adicionar novas tarefas.  
  - `LoginScreen.kt` e `SignupScreen.kt` – Ecrãs de autenticação e registo.  

- **Notificações:**  
  - `NotificationWorker.kt` e `TaskNotificationManager.kt` – Gerem as notificações automáticas para lembretes de tarefas, garantindo que o utilizador não se esqueça dos seus compromissos.  

---

## 🚀 Como Correr o Projeto
### Requisitos
- Android Studio (versão 2022.1 ou superior)  
- SDK Android 30+  
- Emulador ou dispositivo físico para testes

### Passos
1. Clona o repositório:  
   ```bash
   git clone https://github.com/teu-username/taskmanager.git
