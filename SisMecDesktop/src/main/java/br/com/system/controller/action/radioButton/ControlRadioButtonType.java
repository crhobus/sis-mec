package br.com.system.controller.action.radioButton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class ControlRadioButtonType {

    private final List<RadioButtonType> types;

    public ControlRadioButtonType() {
        this.types = new ArrayList<>();
    }

    public void addType(RadioButtonType type) {
        types.add(type);
    }

    public List<RadioButtonType> getTypes() {
        return types;
    }

    public RadioButtonType getTypeV(String valor) {
        return types.stream()
                .filter(t -> t.getValor().equalsIgnoreCase(valor))
                .findFirst().orElse(null);
    }

    public RadioButtonType getTypeD(String descricao) {
        return types.stream()
                .filter(t -> t.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .findFirst().orElse(null);
    }

    public RadioButtonType getTypeI(int index) {
        return types.stream()
                .filter(t -> t.getIndex() == index)
                .findFirst().orElse(null);
    }
}
