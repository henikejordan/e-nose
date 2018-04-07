package modelo;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 * Component JIntegerField
 *
 * @author Henike
 */
public class JIntegerField extends JFormattedTextField {

    private static final long serialVersionUID = -5712106034509737967L;
    private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();

    /**
     * Creates a new instance of JNumericField
     */
    public JIntegerField() {
        this.setHorizontalAlignment(JTextField.LEFT);
        this.setDocument(new IntegerFieldDocument());
        this.addFocusListener(new IntegerFieldFocusListener());
        this.setText("1");
        this.addCaretListener((CaretEvent e) -> {
            if (e.getDot() != getText().length()) {
                getCaret().setDot(getText().length());
            }
        });
    }

    private final class IntegerFieldFocusListener extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            selectAll();
        }
    }

    private final class IntegerFieldDocument extends PlainDocument {

        private static final long serialVersionUID = -3802846632709128803L;

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String original = getText(0, getLength());

            if (original.length() < 7) {
                StringBuilder mascarado = new StringBuilder();
                if (a != nullAttribute) {
                    //limpa o campo  
                    remove(-1, getLength());

                    mascarado.append((original + str).replaceAll("[^0-9]", ""));
                    for (int i = 0; i < mascarado.length(); i++) {
                        if (!Character.isDigit(mascarado.charAt(i))) {
                            mascarado.deleteCharAt(i);
                        }
                    }

                    Integer number = new Integer(mascarado.toString());
                    mascarado.replace(0, mascarado.length(), number.toString());

                    super.insertString(0, mascarado.toString(), a);
                } else if (original.length() == 0) {
                    super.insertString(0, "0", a);
                }
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            if (len == getLength()) {
                super.remove(0, len);
                if (offs != -1) {
                    insertString(0, "", nullAttribute);
                }
            } else {
                String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
                super.remove(0, getLength());
                insertString(0, original, null);
            }
        }
    }

}
