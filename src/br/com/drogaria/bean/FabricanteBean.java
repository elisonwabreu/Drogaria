package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	
	private ListDataModel<Fabricante> itens;
	private Fabricante fabricante;
	

 	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ListDataModel<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fabricante> itens) {
		this.itens = itens;
	}
	
	@PostConstruct
	public void preparaPequisa(){
		try{
		FabricanteDAO dao = new FabricanteDAO();
		ArrayList<Fabricante> lista = dao.listar();
		itens = new ListDataModel<Fabricante>(lista);
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
			
			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);
			
			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemError("Erro ao salvar o Fabricante." + e.getMessage());
		}
		
	}
	
	public void prepararExcluir(){
		fabricante = itens.getRowData();
	}
	
	public void excluir(){
		FabricanteDAO dao = new FabricanteDAO();
		try{
		dao.excluir(fabricante);
		
		ArrayList<Fabricante> lista = dao.listar();
		itens = new ListDataModel<Fabricante>(lista);
		JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
		}catch(SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemError("Erro ao remover o Fabricante." + ex.getMessage());
		}
	}

}
