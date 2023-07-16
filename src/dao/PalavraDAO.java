package dao;

import dicionariocomtela.Palavra;
import java.util.ArrayList;
import java.sql.ResultSet;
import util.ConexaoBD;

public class PalavraDAO {

    public void insert(Palavra p) throws Exception {
        ConexaoBD.getInstance().executeUpdate("INSERT INTO palavras(ds_palavra,ds_significado) VALUES " + "(" + "'" + p.getPalavra() + "'" + "," + "'" + p.getSignificado() + "'" + ")");
    }

    public void update(Palavra p) throws Exception {
        ConexaoBD.getInstance().executeUpdate("UPDATE palavras SET palavra =" + "'" + p.getPalavra() + "'" + "," + "significado =" + "'" + p.getSignificado() + "'" + " WHERE id = " + p.getCodigo());
    }

    public void delete(Palavra p) throws Exception {
        ConexaoBD.getInstance().executeUpdate("DELETE FROM palavras WHERE id = " + p.getCodigo());
    }

    public ArrayList<Palavra> getPalavras() throws Exception {  //Sobrecarga para consultar as palavras quando não passar um filtro
        return getPalavras(null);
    }

    public ArrayList<Palavra> getPalavras(String condicao) throws Exception {
        ArrayList<Palavra> arrayPalavras = new ArrayList();
        String sql = "SELECT * FROM palavras ";

        if (condicao != null && !condicao.isBlank()) {
            sql += "WHERE ds_palavra LIKE '%" + condicao + "%'";
        }
        
        try{                                    //Esse try catch está sendo utilizado para montar a pesquisa com o ID
            Integer.valueOf(condicao);        //Antes estava dando errado, pois a consulta estava saindo com string na coluna de ID
            sql += " OR id = " + condicao;
        }catch(Exception e){

        }
       
        ResultSet rs = ConexaoBD.getInstance().executeQuery(sql);

        while (rs.next()) {
            Palavra p = new Palavra(rs.getString("ds_palavra"), rs.getString("ds_significado"));
            p.setCodigo(rs.getInt("id"));
            arrayPalavras.add(p);
        }
        return arrayPalavras;
    }
    
     public boolean validarPalavra( String palavra, String id ) throws Exception
   {
        boolean result = false;
        
        if ( palavra != null && ! palavra.isBlank() )
        {
            String sql = " select count(*) from palavras where ds_palavra = '" + palavra + "'";
            
            if ( id != null && ! id.isBlank() )
            {
                 sql += " and id <> " + id;
            }

            ResultSet rs = ConexaoBD.getInstance().executeQuery( sql );

            if ( rs.next() )
            {
                result = rs.getInt( 1 ) == 0;
            }
        }
        
        return result;
   }
}
