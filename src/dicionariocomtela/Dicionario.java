package dicionariocomtela;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dicionario {

    private java.util.ArrayList<Palavra> arrayPalavras = new ArrayList();
    private int idPalavras;

    public void setIdPalavras(int idPalavras) {
        this.idPalavras = idPalavras;
    }

    public void addPalavra(Palavra x, char leitura) {
        if (leitura != 's') {
            x.setCodigo(this.idPalavras);
            idPalavras++;
        }
        this.arrayPalavras.add(x);

    }

    public boolean validaPalavra(String nomePalavra) {
        if (nomePalavra == null) {
            return false;
        }

        boolean encontrou = false;

        for (Palavra p : arrayPalavras) {

            if (p.getPalavra().equals(nomePalavra)) {
                encontrou = true;
            }

        }
        return !encontrou;
    }

    public String listaPalavras() {
        boolean encontrou = false;

        String mensagemPane = "";
        for (Palavra p : arrayPalavras) {
            mensagemPane += Integer.toString(p.getCodigo()) + " - " + p.getPalavra() + " - " + p.getSignificado() + "\n";
            encontrou = true;
        }

        if (!encontrou) {
            JOptionPane.showMessageDialog(null, "Dicionário vazio");
        } else {
            JOptionPane.showMessageDialog(null, mensagemPane);
        }
        return mensagemPane;
    }

    public void listaPalavraExata(String palavraAProcurar) {
        boolean encontrou = false;
        String mensagemPane = "";
        for (Palavra p : arrayPalavras) {

            if (p.getPalavra().equals(palavraAProcurar)) {
                mensagemPane += Integer.toString(p.getCodigo()) + " - " + p.getPalavra() + " - " + p.getSignificado() + "\n";
                encontrou = true;
            }
        }

        if (!encontrou) {
            JOptionPane.showMessageDialog(null, "A palavra '" + palavraAProcurar + "' não existe no dicionário");
        } else {
            JOptionPane.showMessageDialog(null, mensagemPane);
        }
    }

    public void listaPalavraSemelhante(String palavraAProcurar) {
        boolean encontrou = false;
        String mensagemPane = "";

        for (Palavra p : arrayPalavras) {
            if (p.getPalavra().contains(palavraAProcurar)) {
                mensagemPane += Integer.toString(p.getCodigo()) + " - " + p.getPalavra() + " - " + p.getSignificado() + "\n";

                encontrou = true;
            }
        }

        if (!encontrou) {
            JOptionPane.showMessageDialog(null, "Nenhuma palavra contém '" + palavraAProcurar + "'.");
        } else {
            JOptionPane.showMessageDialog(null, mensagemPane);
        }
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
