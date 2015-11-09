/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esales.model;

import com.esales.util.MinhaFazendaHibernateUtil;
import java.util.ArrayList;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author cleverton
 */
public class ModelDAO {

    //
    private final SessionFactory objSessionFactory;

    //String para mensagem de erro
    String msg = "";

    public ModelDAO() {
        //Recebe o Session Factory do HIbernate
        this.objSessionFactory = MinhaFazendaHibernateUtil.getSessionFactory();
    }

    public String insert(SystemUser obj) {
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();
        //Inicia uma transação dentro da sessão aberta
        Transaction objTransaction = objSession.beginTransaction();

        try {
            //ADICIONA o objeto, assim o hibernate persiste no bancoapagando o registro.
            objSession.save(obj);
            //Realiza um commit do INSERT
            objTransaction.commit();
        } catch (Exception e) {
            //Caso ocorrer algum erro, mostra uma mensagem
            this.msg = e.getMessage();
            //Realiza o Rollback, cancelando o INSERT no banco de dados.
            objTransaction.rollback();
        }

        //Fecha a sessão
        objSession.close();
        //Retorna a mensagem
        return this.msg;
    }

    public String update(SystemUser obj) {
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();
        //Inicia uma transação dentro da sessão aberta
        Transaction objTransaction = objSession.beginTransaction();

        try {
            //ATUALIZA o objeto, assim o hibernate persiste no bancoapagando o registro.
            objSession.merge(obj);
            //Realiza um commit do UPDATE
            objTransaction.commit();
        } catch (Exception e) {
            //Caso ocorrer algum erro, mostra uma mensagem
            msg = e.getMessage();
            //Realiza o Rollback, cancelando o UPDATE no banco de dados.
            objTransaction.rollback();
        }

        //Fecha a sessão
        objSession.close();
        //Retorna a mensagem
        return msg;
    }

    public String delete(SystemUser obj) {
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();
        //Inicia uma transação dentro da sessão aberta
        Transaction objTransaction = objSession.beginTransaction();

        try {
            //Cria QUERY para excluir o registro
            Query query = objSession.createQuery("delete Usuario where id = :id");
            //Seta os parâmetros
            query.setParameter("id", obj.getId());
            //Executa a QUERY
            query.executeUpdate();
            //Realiza um commit do UPDATE
            objTransaction.commit();
        } catch (Exception e) {
            //Caso ocorrer algum erro, mostra uma mensagem
            msg = e.getMessage();
            //Realiza o Rollback, cancelando o UPDATE no banco de dados.
            objTransaction.rollback();
        }
        //Fecha a sessão
        objSession.close();
        //Retorna a mensagem
        return msg;
    }

    public ArrayList<SystemUser> findByAll() {
        //Cria lista de objetos
        ArrayList<SystemUser> lstUsuario = null;
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();

        try {
            Query objQuery = objSession.createQuery("from Usuario");
            lstUsuario = (ArrayList<SystemUser>) objQuery.list();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        //Fecha a sessão
        objSession.close();
        //Retorna lista
        return lstUsuario;
    }

    public SystemUser findById(int id) {
        //Cria lista de objetos
        SystemUser objUsuario = null;
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();

        try {
            objUsuario = (SystemUser) objSession.load(SystemUser.class, id);
        } catch (ObjectNotFoundException e) {
            return null;
        }
        //Retorna objeto Usuario
        return objUsuario;
    }

    public ArrayList<SystemUser> findByDescricao(String descricao) {
        //Cria lista de objetos
        ArrayList<SystemUser> lstUsuario = null;
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();

        try {
            Query objQuery = objSession.createQuery("from Usuario where usuario like '%" + descricao + "%'");
            lstUsuario = (ArrayList<SystemUser>) objQuery.list();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        //Fecha a sessão
        objSession.close();
        //Retorna lista de Usuarios
        return lstUsuario;
    }

    public ArrayList<SystemUser> findByValidarUsuario(String descricao, String senha) {
        //Cria lista de objetos
        ArrayList<SystemUser> lstUsuario = null;
        //Abre um sessão
        Session objSession = this.objSessionFactory.openSession();

        try {
            Query objQuery = objSession.createQuery("from SystemUser where login = '" + descricao + "' and password = '" + senha + "'");
            lstUsuario = (ArrayList<SystemUser>) objQuery.list();
        } catch (ObjectNotFoundException e) {
            return null;
        }
        //Fecha a sessão
        objSession.close();
        //Retorna lista de Usuarios
        return lstUsuario;
    }

}
