package br.com.system.controller.action.radioButton;

/**
 *
 * @author crhobus
 */
public class RadioButtonType {

    private final int index;
    private final String valor;
    private final String descricao;

    public RadioButtonType(int index, String valor, String descricao) {
        this.index = index;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getIndex() {
        return index;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
