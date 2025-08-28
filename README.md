# 📌 Desafio TDD - Event City

Implemente as funcionalidades necessárias para que os **testes do projeto abaixo passem**  
(veja vídeo explicativo).

---

## 📝 Descrição do sistema

Este é um sistema de **eventos** e **cidades**, com uma relação **N-1** entre eles:
<img width="601" height="189" alt="image" src="https://github.com/user-attachments/assets/20915a1d-9189-4fb4-8a0b-9e3375a58795" />

---

## 🎯 Especificação

A especificação do que deve ser implementado está no próprio código-fonte dos **testes automatizados**.  
Seu objetivo é seguir a abordagem **TDD (Test-Driven Development)**:
## ✅ Critérios de Correção

### 🔹 Cidades
- **DELETE /cities/{id}**
  - Deve retornar **404 Not Found** quando `id` não existir.
  - Deve retornar **204 No Content** quando `id` for **independente**.
  - Deve retornar **400 Bad Request** quando `id` for **dependente**.

- **POST /cities**
  - Deve **inserir recurso**.

- **GET /cities**
  - Deve **retornar recursos ordenados por nome**.

### 🔹 Eventos
- **PUT /events**
  - Deve **atualizar recurso** quando `id` existir.
  - Deve retornar **404 Not Found** quando `id` não existir.

---

## 🧑‍💻 Competências desenvolvidas

- Desenvolvimento **TDD** de API REST com **Java** e **Spring Boot**.
- Implementação de cenários de:
  - Busca  
  - Inserção  
  - Deleção  
  - Atualização  
- Tratamento de exceções em API com **respostas HTTP customizadas**.
