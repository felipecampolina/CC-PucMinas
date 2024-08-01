const express = require("express");
const bodyParser = require("body-parser");
const cors = require('cors');
const connectToDatabase = require("./src/database/connect");
const UserModel = require("./src/models/user.model"); // Importa o modelo de usuário

const app = express();
const port = 8080;

app.use(bodyParser.json(), cors());

// Middleware para lidar com erros
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).send('Erro interno do servidor!');
});

// Função para lidar com o cadastro de conta
async function cadastrarConta(req, res) {
  try {
    // Obtenha os dados enviados no corpo da requisição
    const { firstName, lastName, username, password } = req.body;

    // Cria um novo documento de usuário
    const newUser = new UserModel({
      firstName,
      lastName,
      username,
      password,
    });

    // Salva o novo usuário no banco de dados
    await newUser.save();

    // Responde com o novo usuário criado
    res.status(201).json(newUser);
  } catch (error) {
    console.error("Erro ao cadastrar conta:", error.message);
    res.status(500).json({ error: "Erro ao cadastrar conta" });
  }
}

// Função para lidar com a listagem de todos os usuários
async function listarUsuarios(req, res) {
  try {
    const users = await UserModel.find({});
    res.status(200).json(users);
  } catch (error) {
    console.error("Erro ao listar usuários:", error.message);
    res.status(500).json({ error: "Erro ao listar usuários" });
  }
}

// Função para lidar com a exclusão de um usuário pelo ID
async function excluirUsuario(req, res) {
  try {
    const userId = req.params.id;
    const deletedUser = await UserModel.findByIdAndDelete(userId);

    if (!deletedUser) {
      return res.status(404).json({ error: "Usuário não encontrado" });
    }

    res.status(200).json(deletedUser);
  } catch (error) {
    console.error("Erro ao excluir usuário:", error.message);
    res.status(500).json({ error: "Erro ao excluir usuário" });
  }
}

// Função para lidar com a edição de um usuário pelo ID
async function editarUsuario(req, res) {
  try {
    const userId = req.params.id;
    const { firstName, lastName, username, password } = req.body;

    const updatedUser = await UserModel.findByIdAndUpdate(userId, {
      firstName,
      lastName,
      username,
      password,
    }, { new: true });

    if (!updatedUser) {
      return res.status(404).json({ error: "Usuário não encontrado" });
    }

    res.status(200).json(updatedUser);
  } catch (error) {
    console.error("Erro ao editar usuário:", error.message);
    res.status(500).json({ error: "Erro ao editar usuário" });
  }
}

// Rota para receber dados de cadastro de conta
app.post("/conta/cadastrar", cadastrarConta);

// Rota para listar todos os usuários
app.get("/users", listarUsuarios);

// Rota para excluir um usuário pelo ID
app.delete("/users/:id", excluirUsuario);

// Rota para editar um usuário pelo ID
app.put("/users/:id", editarUsuario);

// Função para iniciar o servidor
function iniciarServidor() {
  app.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}`);
  });
}

// Inicia o servidor
iniciarServidor();

// Conecta ao banco de dados
connectToDatabase();
