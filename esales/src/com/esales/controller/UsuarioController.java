/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esales.controller;

import com.esales.model.Filial;
import com.esales.model.SystemUser;
import com.esales.model.ModelDAO;
//import com.esales.model.UsuarioTipo;
//import com.esales.model.UsuarioTipoModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cleverton
 */
public final class UsuarioController extends AbstractTableModel {

    //Cria o objeto do Model
    private final ModelDAO objModel;
    //Table model
    private ArrayList<SystemUser> lstUsuario = null;
    private SystemUser usuariot = null;
    private String[] column = {"Id", "Name", "Login", "Email"};

    /*
     * Método construtor da classe
     */
    public UsuarioController() {
        //Inicia o objeto model
        this.objModel = new ModelDAO();
        //Inicia a lista da lstColunas para o TableMOdel
        //setColunas(new String[]{"id", "descricao"});  
    }
    
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }
    
    public void setColunas(String[] colunas) {
        this.column = colunas;
    }
    
    public Boolean insert(SystemUser obj) {
//        if (obj.getUsuario().equals("")) {
//            JOptionPane.showMessageDialog(null, "A descrição não foi informada!", "Erro", JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (obj.getUsuario().length() > 45) {
//            JOptionPane.showMessageDialog(null, "Tamanho máximo do campo foi atingido, verifique!", "Verifique", JOptionPane.INFORMATION_MESSAGE);
//            return false;
//        } else {
            
//            obj.setFilial(new Filial(1));
            String msg = objModel.insert(obj);
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
//        }
//        return true;
    }
    
    public Boolean update(SystemUser obj) {
//        if (obj.getUsuario().equals("")) {
//            JOptionPane.showMessageDialog(null, "A descrição não foi informada!", "Erro", JOptionPane.ERROR_MESSAGE);
//            return false;
//        } else if (obj.getUsuario().length() > 45) {
//            JOptionPane.showMessageDialog(null, "Tamanho máximo do campo foi atingido, verifique!", "Verifique", JOptionPane.INFORMATION_MESSAGE);
//            return false;
//        } else {
            String msg = objModel.update(obj);
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
//        }
//        return true;
    }
    
    public Boolean delete(SystemUser obj) {        
        String msg = objModel.delete(obj);
        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    public ArrayList<SystemUser> findByAll() {        
        this.lstUsuario = objModel.findByAll();
        return this.lstUsuario;
    }    
    
    public SystemUser findById(int id){
        return objModel.findById(id);
    }
    
    public ArrayList<SystemUser> findByUsuario(String usuario) {        
        this.lstUsuario = objModel.findByDescricao(usuario);
        return this.lstUsuario;
    }
    
    public ArrayList<SystemUser> findByUsuario(String usuario, String senha) {        
        this.lstUsuario = objModel.findByValidarUsuario(usuario, senha);
        //Valida se a lista de usuário é maior que zero.
        if(this.lstUsuario.size()>0){
         return this.lstUsuario;   
        }else{
            return null;
        }
        
    }
    

    //Métodos implementados pela classe AbstractTableModel
    @Override
    public int getRowCount() {
        return lstUsuario.size();
    }
    
    @Override
    public int getColumnCount() {
        return column.length;        
    }
    
    @Override
    public Object getValueAt(int linhaIndex, int colunaIndex) {
        
        Object value = null;        
        
        final SystemUser c = (SystemUser)lstUsuario.get(linhaIndex);  
        
        switch (colunaIndex) {            
            case 0:                
                value = c.getId();
                break;            
            case 1:                
                value = c.getName();
                break;          
            case 2:
                value = c.getLogin();    
                break; 
            case 3:
                value = c.getEmail();
                break; 
            default:  
                return null;  
        }
        
        return value;
    }
}
