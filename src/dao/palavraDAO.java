package dao;

import dicionariocomtela.Palavra;
import java.util.ArrayList;
import java.sql.ResultSet;
import util.ConexaoBD;

public class palavraDAO {

    public void insert(Palavra p) throws Exception {
        ConexaoBD.getInstance().executeUpdate("INSERT INTO palavras(ds_palavra,ds_significado) VALUES " + "(" + "'" + p.getPalavra() + "'" + "," + "'" + p.getSignificado() + "'" + ")");
    }

    public void update(Palavra p) throws Exception {
        ConexaoBD.getInstance().executeUpdate("UPDATE palavras SET palavra =" + "'" + p.getPalavra() + "'" + "," + "significado =" + "'" + p.getSignificado() + "'" + " WHERE id = " + p.getCodigo());
    }
    
    public void delete(Palavra p) throws Exception{
        ConexaoBD.getInstance().executeUpdate("DELETE FROM palavras WHERE id = "+p.getCodigo());
    }
    
    
    public ArrayList<Palavra> get() throws Exception{  //Sobrecarga para consultar as palavras quando não passar um filtro
        return get(null);
    }
    
    public ArrayList<Palavra> get(String condicao) throws Exception{
        ArrayList<Palavra> arrayPalavras = new ArrayList();
        String sql = "SELECT * FROM palavras";
        
        if(condicao != null && !condicao.isBlank()){
        sql += "WHERE ds_palavra LIKE '%" + condicao+"%' "
                + "OR"
                + "id = "+condicao;
        }
        
        ResultSet rs = ConexaoBD.getInstance().executeQuery(sql);
        
        while(rs.next()){
            Palavra p = new Palavra(rs.getString("palavra"),rs.getString("significado"));
            p.setCodigo(rs.getInt("id"));
            arrayPalavras.add(p);
        }
        return arrayPalavras;
    }
}
