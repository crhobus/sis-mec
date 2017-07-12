package br.com.system.controller.action.comboBox;

/**
 *
 * @author crhobus
 */
public class ComboBoxOption {

    private final String chave;
    private final String descricao;

    public ComboBoxOption(String chave, String descricao) {
        this.chave = chave;
        this.descricao = descricao;
    }

    public String getChave() {
        return chave;
    }

    public String getDescricao() {
        return descricao;
    }
}
