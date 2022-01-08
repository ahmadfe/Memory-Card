import javax.swing.*;
import java.awt.Image;
public class Card {

    // true = exposed , false = unexposed
    private boolean status;
    // 0 = unresolved , 1 = resolved by player 1 , 2 = resolved by player 2
    private int resolved;
    private int index;
    private String imageSorce;
    private ImageIcon imageIcone;
    public JButton button;

    public Card() {}

    public Card(JButton button, String imageSorce) {
        this.button = button;
        this.status = false;
        this.resolved = 0;
        this.imageSorce = imageSorce;
        Image image = ((new ImageIcon(imageSorce)).getImage()).getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it smoothly
        this.imageIcone = new ImageIcon(image);
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public void setIndex(int index) {
        this.index = index;
        this.button.setText((String.valueOf(this.index)));
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setImageSorce(String imageSorce) {
        this.imageSorce = imageSorce;
    }

    public void setImageIcon(ImageIcon imageIcone) {
        this.imageIcone = imageIcone;
    }

    public boolean getStatus() {
        return this.status;
    }

    public int getResolved() {
        return this.resolved;
    }

    public int getIndex() {
        return this.index;
    }

    public JButton getButton() {
        return this.button;
    }

    public String getImageSorce() {
        return this.imageSorce;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcone;
    }

    public void showImage() {
        this.button.setText("");
        this.button.setIcon(imageIcone);
        this.status = true;
    }

    public void hideImage() {
        this.button.setText((String.valueOf(this.index)));
        this.button.setIcon(null);
        this.status = false;
    }
}