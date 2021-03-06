package figury;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                final AnimatorApp frame = new AnimatorApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 * @param delay 
	 */
	public AnimatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = 450, wh = 300;
		setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AnimPanel kanwa = new AnimPanel();
		kanwa.setBounds(10, 11, 422, 219);
		contentPane.add(kanwa);
		SwingUtilities.invokeLater(kanwa::initialize);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> kanwa.addFig());
		btnAdd.setBounds(10, 239, 80, 23);
		contentPane.add(btnAdd);
		
		JButton btnAnimate = new JButton("Animate");
		btnAnimate.addActionListener(e -> kanwa.animate());
		btnAnimate.setBounds(100, 239, 80, 23);
		contentPane.add(btnAnimate);

		JButton btnTrace = new JButton("Traces");
		btnTrace.addActionListener(e-> {
			if (kanwa.getTrace())
				kanwa.setTrace(false);
			else
				kanwa.setTrace(true);
		});
		btnTrace.setBounds(190,239,80,23);
		contentPane.add(btnTrace);

		addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent evt) {
				Component c = (Component)evt.getSource();
				kanwa.setBounds(10,11,c.getWidth()-35, c.getHeight()-80);
				btnAdd.setBounds(10,c.getHeight()-65,80,23);
				btnAnimate.setBounds(100,c.getHeight()-65,80,23);
				btnTrace.setBounds(190,c.getHeight()-65,80,23);
				kanwa.Update();
			}
		});
	}

}
