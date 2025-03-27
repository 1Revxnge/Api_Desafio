
const apiUrl = 'http://localhost:8080/tasks';


const taskForm = document.getElementById('task-form');
const clearFormButton = document.getElementById('clear-form');


function getFormData() {
    return {
        nome: document.getElementById('nome').value,
        cpf: document.getElementById('cpf').value,
        endereco: document.getElementById('endereco').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        contato: document.getElementById('contato').value,
        contatoValor: document.getElementById('contatoValor').value
    };
}


async function addTask(event) {
    event.preventDefault(); 

    const taskData = getFormData();

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            const newTask = await response.json();
            displayTasks(); 
            clearForm(); 
        } else {
            alert('Erro ao cadastrar o cliente');
        }
    } catch (error) {
        console.error('Erro ao enviar dados:', error);
        alert('Erro ao enviar dados para o servidor');
    }
}

// Função para limpar o formulário
function clearForm() {
    document.getElementById('nome').value = '';
    document.getElementById('cpf').value = '';
    document.getElementById('endereco').value = '';
    document.getElementById('dataNascimento').value = '';
    document.getElementById('contato').value = 'email'; 
    document.getElementById('contatoValor').value = '';
}


async function displayTasks() {
    const taskList = document.getElementById('task-list');
    taskList.innerHTML = ''; 

    try {
        const response = await fetch(apiUrl);
        const tasks = await response.json();

        tasks.forEach(task => {
            const li = document.createElement('li');
            li.innerHTML = `
                Nome: ${task.nome} | CPF: ${task.cpf} | Contato: ${task.contatoValor}
                <button onclick="editTask(${task.id})">Editar</button>
                <button onclick="deleteTask(${task.id})">Deletar</button>
            `;
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error('Erro ao carregar tarefas:', error);
        alert('Erro ao carregar as tarefas');
    }
}


async function editTask(taskId) {
    const taskData = await fetch(`${apiUrl}/${taskId}`);
    const task = await taskData.json();

    document.getElementById('nome').value = task.nome;
    document.getElementById('cpf').value = task.cpf;
    document.getElementById('endereco').value = task.endereco;
    document.getElementById('dataNascimento').value = task.dataNascimento;
    document.getElementById('contato').value = task.contato;
    document.getElementById('contatoValor').value = task.contatoValor;

    taskForm.onsubmit = function(event) {
        event.preventDefault();
        updateTask(taskId);
    };
}

// Função para atualizar a tarefa
async function editTask(taskId) {
    const taskData = await fetch(`${apiUrl}/${taskId}`);
    const task = await taskData.json();

    document.getElementById('nome').value = task.nome;
    document.getElementById('cpf').value = task.cpf;
    document.getElementById('endereco').value = task.endereco;

    
    if (task.dataNascimento) {
        const formattedDate = new Date(task.dataNascimento).toISOString().split('T')[0];
        document.getElementById('dataNascimento').value = formattedDate;
    }

    document.getElementById('contato').value = task.contato;
    document.getElementById('contatoValor').value = task.contatoValor;

    taskForm.onsubmit = function(event) {
        event.preventDefault();
        updateTask(taskId);
    };
}

// Função para deletar a tarefa
async function deleteTask(taskId) {
    try {
        const response = await fetch(`${apiUrl}/${taskId}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            displayTasks(); 
        } else {
            alert('Erro ao deletar a tarefa');
        }
    } catch (error) {
        console.error('Erro ao deletar a tarefa:', error);
        alert('Erro ao deletar a tarefa');
    }
}


taskForm.addEventListener('submit', addTask);


clearFormButton.addEventListener('click', clearForm);


window.onload = displayTasks;
