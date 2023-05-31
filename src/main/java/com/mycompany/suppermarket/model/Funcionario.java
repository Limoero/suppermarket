/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.model;

/**
 *
 * @author diego.mvogelsanger
 */
public class Funcionario {
    private String nome;
    private String rg;
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        
        
        
    }

    public Funcionario() {
    }

    public Funcionario(String nome, String rg, String cpf) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
    }
    
    // Método que verifica se um CPF é válido
    public static boolean validarCPF(String cpf) {
  // Remove os caracteres não numéricos do CPF
  cpf = cpf.replaceAll("[^\\d]", "");
  
  // Verifica se o CPF tem 11 dígitos
  if (cpf.length() != 11) {
    return false;
  }
  
  // Calcula o primeiro dígito verificador
  int soma = 0;
  for (int i = 0; i < 9; i++) {
    soma += (cpf.charAt(i) - '0') * (10 - i);
  }
  int resto = soma % 11;
  int primeiroDigito = (resto < 2) ? 0 : (11 - resto);
  
  // Verifica se o primeiro dígito verificador é igual ao esperado
  if (primeiroDigito != (cpf.charAt(9) - '0')) {
    return false;
  }
  
  // Calcula o segundo dígito verificador
  soma = 0;
  for (int i = 0; i < 10; i++) {
    soma += (cpf.charAt(i) - '0') * (11 - i);
  }
  resto = soma % 11;
  int segundoDigito = (resto < 2) ? 0 : (11 - resto);
  
  // Verifica se o segundo dígito verificador é igual ao esperado
  if (segundoDigito != (cpf.charAt(10) - '0')) {
    return false;
  }
  
  // Se chegou até aqui, o CPF é válido
  return true;
}
    
    // Método que verifica se um RG é válido
    public static boolean validarRG(String rg) {
  // Remove os caracteres não numéricos do RG
  rg = rg.replaceAll("[^\\d]", "");
  
  // Verifica se o RG tem 9 dígitos
  if (rg.length() != 9) {
    return false;
  }
  
  // Calcula o dígito verificador
  int soma = 0;
  for (int i = 0; i < 8; i++) {
    soma += (rg.charAt(i) - '0') * (2 + i);
  }
  int resto = soma % 11;
  int digito = (resto == 0 || resto == 1) ? 0 : (11 - resto);
  
  // Verifica se o dígito verificador é igual ao esperado
  if (digito != (rg.charAt(8) - '0')) {
    return false;
  }
  
  // Se chegou até aqui, o RG é válido
  return true;
}
    
}
