import javax.swing.*;

public abstract class Form {
    protected JFrame frame;

    public Form(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        initializeComponents();
    }

    protected abstract void initializeComponents();

    public void show() {
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    
    public JFrame getFrame() {
        return frame;
    }
}
