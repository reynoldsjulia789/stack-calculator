package src.View;

import src.Controller.InputProcessor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * TODO: take keyboard input
 * GUI Calculator
 * @author CSCD 300 homework 5/6 starter code
 */
public class CalculatorGUI extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	// Buttons
	JButton btn1 		= new JButton("1");
	JButton btn2 		= new JButton("2");
	JButton btn3 		= new JButton("3");
	JButton btn_add 	= new JButton("+");
	JButton btn4 		= new JButton("4");
	JButton btn5 		= new JButton("5");
	JButton btn6 		= new JButton("6");
	JButton btn_sub 	= new JButton("-");
	JButton btn7 		= new JButton("7");
	JButton btn8 		= new JButton("8");
	JButton btn9 		= new JButton("9");
	JButton btn_mult 	= new JButton("*");
	JButton btn0 		= new JButton("0");
	JButton btn_dot 	= new JButton(".");
	JButton btn_del 	= new JButton("DEL");
	JButton btn_div 	= new JButton("/");
	
	JButton btn_lpr 	= new JButton("(");
	JButton btn_rpr 	= new JButton(")");
	JButton btn_pow 	= new JButton("^");
	JButton btn_equ 	= new JButton("=");

	// text field
	JTextField txt		  = new JTextField();
	JTextField txtHistory = new JTextField();

	// input processor to keep track of expression
	InputProcessor input = new InputProcessor();

	public CalculatorGUI()
	{
		JFrame frame = new JFrame("Stack Calculator");
		frame.setSize(320,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setVisible(true);
		frame.setLayout(new GridLayout(2,1));
		
		JPanel HeadPanel   = new JPanel();
		JPanel NumberPanel = new JPanel();
		JPanel LabelPanel  = new JPanel();
		
		LabelPanel.setBackground(Color.LIGHT_GRAY);
		HeadPanel.setBackground(Color.LIGHT_GRAY);
		NumberPanel.setLayout(new GridLayout(5,4));
		//LabelPanel.setLayout(new BorderLayout());
		LabelPanel.setLayout(new GridLayout(2,1));
		
		NumberPanel.add(btn1);
		btn1.addActionListener(this);
		NumberPanel.add(btn2);
		btn2.addActionListener(this);
		NumberPanel.add(btn3);
		btn3.addActionListener(this);
		NumberPanel.add(btn_add);
		btn_add.addActionListener(this);
		
		NumberPanel.add(btn4);
		btn4.addActionListener(this);
		NumberPanel.add(btn5);

		btn5.addActionListener(this);
		NumberPanel.add(btn6);
		btn6.addActionListener(this);
		NumberPanel.add(btn_sub);
		btn_sub.addActionListener(this);

		NumberPanel.add(btn7);
		btn7.addActionListener(this);
		NumberPanel.add(btn8);
		btn8.addActionListener(this);
		NumberPanel.add(btn9);
		btn9.addActionListener(this);
		NumberPanel.add(btn_mult);
		btn_mult.addActionListener(this);
		
		NumberPanel.add(btn0);
		btn0.addActionListener(this);
		NumberPanel.add(btn_dot);
		btn_dot.addActionListener(this);
		NumberPanel.add(btn_del);
		btn_del.addActionListener(this);
		NumberPanel.add(btn_div);
		btn_div.addActionListener(this);

		NumberPanel.add(btn_lpr);
		btn_lpr.addActionListener(this);
		NumberPanel.add(btn_rpr);
		btn_rpr.addActionListener(this);
		NumberPanel.add(btn_pow);
		btn_pow.addActionListener(this);
		NumberPanel.add(btn_equ);
		btn_equ.addActionListener(this);

		txt.setFont(new Font("Arial", Font.BOLD, 14));
		txt.setHorizontalAlignment(JTextField.CENTER);
		txt.setEditable(false);
		txt.setBorder(null);

		txtHistory.setFont(new Font("Arial", Font.PLAIN, 10));
		txtHistory.setForeground(Color.GRAY);
		txtHistory.setHorizontalAlignment(JTextField.CENTER);
		txtHistory.setEditable(false);
		txtHistory.setBorder(null);

		LabelPanel.add(txtHistory);
		LabelPanel.add(txt);

		//btn_del.setEnabled(false);
		HeadPanel.add(new JLabel("Stack Calculator"));
		frame.add(LabelPanel);
		frame.add(NumberPanel);
		frame.setVisible(true);
	}

	/**
	 * Listens for user input and updates expression accordingly.
	 * @param e the event to be processed
	 * @author CSCD 300 homework 5/6 starter code
	 * @author Julia Reynolds - updated to use String Builder for easier string manipulation and
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btn1)
		{
			txt.setText(input.pressed("1"));
		}
		else if (e.getSource() == btn2)
		{
			txt.setText(input.pressed("2"));
		}
		else if (e.getSource() == btn3)
		{
			txt.setText(input.pressed("3"));
		}
		else if (e.getSource() == btn4)
		{
			txt.setText(input.pressed("4"));
		}
		else if (e.getSource() == btn5)
		{
			txt.setText(input.pressed("5"));
		}
		else if (e.getSource() == btn6)
		{
			txt.setText(input.pressed("6"));
		}
		else if (e.getSource() == btn7)
		{
			txt.setText(input.pressed("7"));
		}
		else if (e.getSource() == btn8)
		{
			txt.setText(input.pressed("8"));
		}
		else if (e.getSource() == btn9)
		{
			txt.setText(input.pressed("9"));
		}
		else if (e.getSource() == btn0)
		{
			txt.setText(input.pressed("0"));
		}
		else if (e.getSource() == btn_lpr)
		{
			txt.setText(input.pressed("("));
		}
		else if (e.getSource() == btn_rpr)
		{
			txt.setText(input.pressed(")"));
		}
		else if (e.getSource() == btn_pow)
		{
			txt.setText(input.pressed(" ^ "));
		}
		else if (e.getSource() == btn_add)
		{
			txt.setText(input.pressed(" + "));
		}
		else if (e.getSource() == btn_sub)
		{
			txt.setText(input.pressed(" - "));
		}
		else if (e.getSource() == btn_mult)
		{
			txt.setText(input.pressed(" * "));
		}
		else if (e.getSource() == btn_div)
		{
			txt.setText(input.pressed(" / "));
		}
		else if (e.getSource() == btn_dot)
		{
			txt.setText(input.pressed("."));
		}
		else if (e.getSource() == btn_del)
		{
			txt.setText(input.deleteLastInput());
		}
		else if (e.getSource() == btn_equ)
		{
			txt.setText(input.compute());
			txtHistory.setText(input.getLastExpression());
		}
	}
    
	public static void main(String[] args)
	{
		new CalculatorGUI();
	}
}

