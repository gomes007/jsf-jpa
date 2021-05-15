package br.com.repository;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

public class IDaoPessoaImpl implements IDaoPessoa {

	@Override
	public Pessoa consultarUsuario(String login, String senha) {
		
		Pessoa pessoa = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();	
		
		pessoa = (Pessoa) entityManager.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'").getSingleResult();
		
		entityTransaction.commit();
		entityManager.close();			
		
		return pessoa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItem> listaEstados() {
		
		List<SelectItem> selectItems = new ArrayList<SelectItem>(); //tipo lista que é aceito na tela
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();			
		
		List<Estados> estados = entityManager.createQuery(" from Estados").getResultList(); //faz um select/conulta hql armazenando em uma lista
		
		for (Estados estado : estados) {//varre a lista estados e armazena na variavel estado
			selectItems.add(new SelectItem(estado, estado.getNome())); //add a variavel estado ao tipo lista aceito na tela transformando para ficar compreesivo para o combo box na tela
		}
		
		return selectItems; //retorna a lista no formato correto
	}

}
