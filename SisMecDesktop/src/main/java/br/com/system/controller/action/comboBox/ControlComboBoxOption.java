package br.com.system.controller.action.comboBox;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class ControlComboBoxOption {

    private final List<ComboBoxOption> options;

    public ControlComboBoxOption() {
        this.options = new ArrayList<>();
        this.options.add(new ComboBoxOption("0", "Selecione"));
    }

    public void addOption(ComboBoxOption option) {
        options.add(option);
    }

    public List<ComboBoxOption> getOptions() {
        return options;
    }

    public ComboBoxOption getOptionC(String chave) {
        return options.stream()
                .filter(p -> p.getChave().toLowerCase().contains(chave.toLowerCase()))
                .findFirst().orElse(null);
    }

    public ComboBoxOption getOptionD(String descricao) {
        return options.stream()
                .filter(p -> p.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .findFirst().orElse(null);
    }
}
