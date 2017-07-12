package br.com.system.view.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author crhobus
 */
public class WTextInputNumber extends PlainDocument {

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        char digito;
        boolean numerico = true;
        for (int i = 0; i < str.length(); i++) {
            digito = str.charAt(i);
            if (!Character.isDigit(digito)) {
                numerico = false;
                break;
            }
        }
        if (numerico) {
            super.insertString(offs, str, a);
        }
    }
}
