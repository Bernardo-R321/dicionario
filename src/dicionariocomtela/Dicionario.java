package dicionariocomtela;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.PalavraDAO;

public class Dicionario {

    private PalavraDAO palavraDAO = new PalavraDAO();

    private java.util.ArrayList<Palavra> arrayPalavras = new ArrayList();
    private int idPalavras;

    public void setIdPalavras(int idPalavras) {
        this.idPalavras = idPalavras;
    }

    public void addPalavra(Palavra p) {
        
        try {

            if(palavraDAO.validarPalavra(p.getPalavra(), Integer.toString(p.getCodigo())) && p.getCodigo() == 0){
                
                palavraDAO.insert(p);
                
            }else{
                if(p.getCodigo() != 0){
                    
                    palavraDAO.update(p);
                    JOptionPane.showMessageDialog(null,"Palavra atualizada!");
                    
                }else{
                    
                JOptionPane.showMessageDialog(null, "Palavra já está cadastrada!");
                
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }
            
        
    }
    
    public void deletarPalavra(Palavra p){
        int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar esse registro?");
        if(escolha == 0){
            try{
                palavraDAO.delete(p);
                JOptionPane.showMessageDialog(null, "Palavra excluída com sucesso!");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

//    public boolean validaPalavra(String nomePalavra) {
//        if (nomePalavra == null) {
//            return false;
//        }
//
//        boolean encontrou = false;
//
//        for (Palavra p : arrayPalavras) {
//
//            if (p.getPalavra().equals(nomePalavra)) {
//                encontrou = true;
//            }
//
//        }
//        return !encontrou;
//    }
    
    public ArrayList<Palavra> listaPalavras(){
        return listaPalavras("");
    }

    public ArrayList<Palavra> listaPalavras(String filtro) {
        try{
            
        return palavraDAO.getPalavras(filtro);
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            
        }
        return null;
    }

    public void alteraSignificado() {
        int codigo = Entrada.leiaInt("Qual código da palavra?");

        Palavra selecionada = null;

        for (Palavra p : arrayPalavras) {

            if (p.getCodigo() == codigo) {
                selecionada = p;
            }
        }

        if (selecionada != null) {
            String mensagem = "Você selecionou a seguinte palavra:\n"
                    + "Código: " + selecionada.getCodigo() + "\n"
                    + "Palavra: " + selecionada.getPalavra() + "\n"
                    + "Significado: " + selecionada.getSignificado() + "\n\n"
                    + "Qual é o novo significado?";

            selecionada.setSignificado(Entrada.leiaString(mensagem));

            String msg = ("Novo significado: " + selecionada.getSignificado());
            JOptionPane.showMessageDialog(null, msg);

        } else {
            JOptionPane.showMessageDialog(null, "Código inválido!");
        }

    }

    public String formatarPalavras() {
        String palavrasFormatadas = Integer.toString(idPalavras) + "\n";

        for (Palavra p : arrayPalavras) {
            palavrasFormatadas += Integer.toString(p.getCodigo()) + ";" + p.getPalavra() + ";" + p.getSignificado() + "\n"; //Formata as palavras do array em uma string com quebra de linha ao fim de cada palavra
        }

        return palavrasFormatadas;
    }

    public void removerPalavra() {
        listaPalavras();
        int codigoParaProcurar = Entrada.leiaInt("Digite o código da palavra que quer remover: ");
        Palavra palavraParaApagar = null;
        for (Palavra p : arrayPalavras) {
            if (p.getCodigo() == codigoParaProcurar) {
                palavraParaApagar = p;
            }
        }
        if (palavraParaApagar == null) {
            JOptionPane.showMessageDialog(null, "Palavra não encontrada!");
        } else {
            int decisao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar essa palavra?", "Confirmação", 0); /// 0 - Sim 1 - Não 2 - Cancel
            if (decisao == 0) {
                arrayPalavras.remove(palavraParaApagar);
            } else {
                JOptionPane.showMessageDialog(null, "Remoção interrompida!");
            }
        }
    }
}
