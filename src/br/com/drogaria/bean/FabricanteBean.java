package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;
	private Fabricante fabricante;
	

 	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	
	@PostConstruct
	public void preparaPequisa(){
		try{
		FabricanteDAO dao = new FabricanteDAO();
		itens = dao.listar();
		
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemError("Erro ao pesquisar Fabricantes." + ex.getMessage());
		}
	}
	
	public void prepararNovoFab(){
		fabricante =  new Fabricante();
	}
	
	public void novoFabricante(){
		
		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.inserir(fabricante);
			
			 itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemError("Erro ao salvar o Fabricante." + e.getMessage());
		}
		
	}
	
	
	public void excluir(){
		FabricanteDAO dao = new FabricanteDAO();
		try{
		dao.excluir(fabricante);
		
		itens = dao.listar();
		JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemError("Erro ao remover o Fabricante." + ex.getMessage());
		}
	}
	
	
	
	public void editar(){
		FabricanteDAO dao = new FabricanteDAO();
		try{
		dao.editar(fabricante);
		itens = dao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso.");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemSucesso("Erro ao editar o Fabricante." + ex.getMessage());
		}
	}

}
