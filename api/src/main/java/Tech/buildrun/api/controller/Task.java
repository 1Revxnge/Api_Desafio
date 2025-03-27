package Tech.buildrun.api.controller;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // A chave primária é gerada automaticamente
    private Integer id;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate dataNascimento;  // LocalDate para Data de Nascimento
    private String contato;
    private String contatoValor;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getContatoValor() { return contatoValor; }
    public void setContatoValor(String contatoValor) { this.contatoValor = contatoValor; }
}
