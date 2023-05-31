/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.suppermarket.model;

/**
 *
 * @author diego.mvogelsanger
 */
public class Fornecedor {
    private String nome;
    private String cnpj;
    private String ie;
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Fornecedor(String nome, String cnpj, String ie, Endereco endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.ie = ie;
        this.endereco = endereco;
    }
    
    public static boolean validaCNPJ(String cnpj) {
    if (cnpj == null || cnpj.length() != 14)
        return false;

    try {
        Long.valueOf(cnpj);
    } catch (NumberFormatException e) {
        return false;
    }

    int[] weights = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    int[] cnpjIntArray = new int[14];
    for (int i = 0; i < cnpj.length(); i++) {
        cnpjIntArray[i] = Integer.parseInt(cnpj.substring(i, i + 1));
    }

    int sum = 0;
    for (int i = 0; i < 12; i++) {
        sum += cnpjIntArray[i] * weights[i + 1];
    }

    int remainder = sum % 11;
    if (remainder < 2) {
        cnpjIntArray[12] = 0;
    } else {
        cnpjIntArray[12] = 11 - remainder;
    }

    sum = 0;
    for (int i = 0; i < 13; i++) {
        sum += cnpjIntArray[i] * weights[i];
    }

    remainder = sum % 11;
    if (remainder < 2) {
        cnpjIntArray[13] = 0;
    } else {
        cnpjIntArray[13] = 11 - remainder;
    }

    return cnpj.endsWith(cnpjIntArray[12] + "" + cnpjIntArray[13]);
}
}
