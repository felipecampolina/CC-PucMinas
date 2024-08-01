document.addEventListener("DOMContentLoaded", function () {
    const tableBody = document.querySelector("tbody");

    // Função para carregar os usuários na tabela
    async function loadUsers() {
        try {
            const response = await fetch("http://localhost:8080/users");
            const users = await response.json();

            // Limpa a tabela antes de adicionar os novos usuários
            tableBody.innerHTML = "";

            users.forEach((user) => {
                addUserToTable(user);
            });
        } catch (error) {
            console.error("Erro ao carregar usuários:", error.message);
        }
    }

    // Função para adicionar um usuário à tabela
    function addUserToTable(user) {
        const newRow = document.createElement("tr");
        newRow.dataset.userId = user._id; // Adiciona o ID do usuário ao atributo 'data-user-id'

        newRow.innerHTML = `
            <td>${user._id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.username}</td>
            <td>
                <button class="btn btn-warning edit-btn">Editar</button>
                <button class="btn btn-danger delete-btn">Excluir</button>
            </td>
        `;

        tableBody.appendChild(newRow);
    }

    // Função para adicionar um novo usuário
    async function addUser(firstName, lastName, username, password) {
        try {
            const response = await fetch("http://localhost:8080/users", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    firstName,
                    lastName,
                    username,
                    password,
                }),
            });

            if (!response.ok) {
                throw new Error("Erro ao adicionar usuário.");
            }

            const newUser = await response.json();
            addUserToTable(newUser);
        } catch (error) {
            console.error("Erro ao adicionar usuário:", error.message);
        }
    }

// Função para editar um usuário pelo ID
// Função para editar um usuário pelo ID
async function editUser(userId) {
    try {
      // Obter novos dados do usuário
      const userEditData = prompt("Digite os novos dados do usuário (FirstName, LastName, Username, Password):");
      
      if (userEditData) {
        const [newFirstName, newLastName, newUsername, newPassword] = userEditData.split(",");
  
        // Realizar a chamada para o backend para editar o usuário
        const response = await fetch(`http://localhost:8080/users/${userId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            firstName: newFirstName,
            lastName: newLastName,
            username: newUsername,
            password: newPassword,
          }),
        });
  
        if (!response.ok) {
          throw new Error("Erro ao editar usuário.");
        }
  
        const updatedUser = await response.json();
        console.log("Usuário editado com sucesso:", updatedUser);
  
        // Aqui você pode atualizar a tabela na interface com os novos dados
        // ou realizar outras ações necessárias após a edição.
      } else {
        console.log("Edição de usuário cancelada");
      }
    } catch (error) {
      console.error("Erro ao editar usuário:", error.message);
    }
  }


    // Função para excluir um usuário pelo ID
    async function deleteUser(userId) {
        try {
            const response = await fetch(`http://localhost:8080/users/${userId}`, {
                method: "DELETE",
            });

            if (!response.ok) {
                throw new Error("Erro ao excluir usuário.");
            }

            const deletedUser = await response.json();
            console.log("Usuário excluído com sucesso:", deletedUser);

            // Remova a linha da tabela correspondente ao usuário excluído
            const deletedRow = document.querySelector(`[data-user-id="${userId}"]`);
            deletedRow.remove();
        } catch (error) {
            console.error("Erro ao excluir usuário:", error.message);
        }
    }

    // Adiciona um ouvinte de evento ao elemento pai (delegação de eventos)
    tableBody.addEventListener("click", function (event) {
        if (event.target.classList.contains("edit-btn")) {
            // Se o botão clicado for "Editar"
            const userId = event.target.closest("tr").dataset.userId;
            editUser(userId);
        } else if (event.target.classList.contains("delete-btn")) {
            // Se o botão clicado for "Excluir"
            const userId = event.target.closest("tr").dataset.userId;
            deleteUser(userId);
        }
    });

    // Carrega os usuários ao carregar a página
    loadUsers();
});
