# 🛒 Sistema de E-commerce em Java

## 📋 Sobre o Projeto

Sistema de e-commerce desenvolvido em **Java puro** utilizando apenas recursos nativos da linguagem, sem frameworks externos. O projeto simula um ambiente real de comércios eletrônicos com dois perfis de usuário distintos (Administrador e Cliente), implementando regras de negócio consistentes e uma arquitetura organizada em camadas.

**Objetivo:** Consolidar fundamentos de Programação Orientada a Objetos e boas práticas de arquitetura antes da transição para Spring Boot.

---

## 🎯 Funcionalidades

### 👨‍💼 Perfil Administrador
- ✅ Cadastrar produtos no sistema
- ✅ Gerenciar cadastro de clientes
- ✅ Criar e visualizar pedidos
- ✅ Listar produtos disponíveis
- ✅ Consultar clientes registrados
- ✅ Acompanhar todos os pedidos

### 👤 Perfil Cliente
- ✅ Criar conta no sistema
- ✅ Montar carrinho de compras
- ✅ Adicionar produtos ao pedido
- ✅ Visualizar total do pedido
- ✅ Acompanhar status da compra
- ✅ Consultar itens do pedido

---

## 🏗️ Arquitetura

O projeto segue uma separação clara de responsabilidades, organizando o código em camadas:

```
src/
├── Main.java                      # Interface com usuário (menu e I/O)
├── controller/
│   └── SistemaEcommerce.java     # Lógica de negócio e orquestração
└── models/
    ├── Cliente.java               # Entidade Cliente
    ├── Produto.java               # Entidade Produto
    ├── Pedido.java                # Entidade Pedido
    ├── ItemPedido.java            # Entidade ItemPedido
    └── StatusPedido.java          # Enum de Status
```

### Responsabilidades das Camadas

#### 📱 Main
- Interação com o usuário via console
- Exibição de menus e leitura de entradas (`Scanner`)
- Delegação de operações ao Controller
- **Não contém regras de negócio**

#### 🎮 Controller (SistemaEcommerce)
- Centralização das regras de negócio
- Gerenciamento de produtos, clientes e pedidos
- Validações e controle de fluxo
- Encapsulamento das listas internas

#### 📦 Models
- Representação do domínio da aplicação
- Dados e comportamentos das entidades
- Aplicação de princípios OOP (Encapsulamento, Associação)

---

## ⚙️ Regras de Negócio Implementadas

| Regra | Descrição |
|-------|-----------|
| **Controle de Estoque** | Estoque é validado e reduzido automaticamente ao adicionar itens ao pedido |
| **Status do Pedido** | Fluxo controlado: `CRIADO` → `PAGO` → `ENVIADO` |
| **Bloqueio de Edição** | Pedidos com status `CANCELADO` ou `ENVIADO` não podem receber novos itens |
| **Preço Congelado** | Preço do produto é armazenado no item no momento da compra |
| **Cálculo Dinâmico** | Total do pedido calculado com base nos itens adicionados |

---

## 💻 Conceitos de Java Utilizados

- ✅ **Programação Orientada a Objetos (POO)**
- ✅ **Encapsulamento** e **Associação entre classes**
- ✅ **Enum** (`StatusPedido`)
- ✅ **Collections** (`ArrayList`)
- ✅ **Construtores** e **Métodos**
- ✅ **Scanner** para entrada de dados
- ✅ **Switch/Case** para controle de fluxo
- ✅ **AtomicInteger** para geração de IDs únicos
- ✅ **LocalDate** para controle de datas
- ✅ **Separação em camadas** (base para arquitetura MVC)

---

## 🎓 Importância do Projeto

Este projeto foi desenvolvido **intencionalmente sem frameworks**, com os seguintes objetivos:

1. **Dominar fundamentos:** Lógica de negócio, estrutura de aplicações Java e organização de código
2. **Entender arquitetura:** Base para compreensão de padrões utilizados em frameworks modernos
3. **Preparação para Spring:** Após este projeto, será realizada a transição para Spring Boot

> **"Você não pode usar bem um framework se não entende o que ele pode fazer por você."**

---

## 🚀 Como Executar

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/Ecommerce.git
   ```

2. **Abra o projeto em uma IDE Java**
   - IntelliJ IDEA
   - Eclipse
   - VS Code (com extensão Java)

3. **Execute a classe `Main.java`**
   - O sistema iniciará no console

4. **Interaja com o menu**
   - Escolha o perfil (Administrador ou Cliente)
   - Navegue pelas opções disponíveis

---

## 🔮 Próximas Evoluções Planejadas

- [ ] Persistência em arquivo (serialização) ou banco de dados
- [ ] Sistema de login com autenticação
- [ ] Controle de permissões por perfil
- [ ] Refatoração para **Spring Boot**
- [ ] Implementação de camada Service
- [ ] Criação de testes automatizados (JUnit)
- [ ] API REST com endpoints
- [ ] Interface web (Thymeleaf ou React)

---

## 📚 Aprendizados

Este projeto proporcionou experiência prática em:

- Organização de código em camadas
- Implementação de regras de negócio complexas
- Gerenciamento de estado da aplicação
- Relacionamento entre entidades
- Separação de responsabilidades (Separation of Concerns)
- Fundamentos essenciais para desenvolvimento com Spring Framework

---

## 🤝 Contribuições

Contribuições, issues e sugestões são bem-vindas!  
Sinta-se à vontade para abrir uma issue ou enviar um pull request.

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!**
