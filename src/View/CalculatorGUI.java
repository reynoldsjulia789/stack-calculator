package src.View;

import src.Model.Stack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * TODO: take keyboard input
 * GUI Calculator
 * @author CSCD 300 homework 5/6 starter code
 */
class CalculatorGUI extends JFrame implements ActionListener
{

	private static Stack<Integer> charsAdded = new Stack<>();

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

	// string builder to keep track of expression
	StringBuilder str_number = new StringBuilder();

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
			str_number.append("1");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn2)
		{
			str_number.append("2");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn3)
		{
			str_number.append("3");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn4)
		{
			str_number.append("4");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn5)
		{
			str_number.append("5");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn6)
		{
			str_number.append("6");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn7)
		{
			str_number.append("7");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn8)
		{
			str_number.append("8");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn9)
		{
			str_number.append("9");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn0)
		{
			str_number.append("0");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn_lpr)
		{
			str_number.append(" ( ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_rpr)
		{
			str_number.append(" ) ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_pow)
		{
			str_number.append(" ^ ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_add)
		{
			str_number.append(" + ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_sub)
		{
			str_number.append(" - ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_mult)
		{
			str_number.append(" * ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_div)
		{
			str_number.append(" / ");
			txt.setText(str_number.toString());
			charsAdded.push(3);
		}
		else if (e.getSource() == btn_dot)
		{
			System.out.println("you clicked dot button!");
			str_number.append(".");
			txt.setText(str_number.toString());
			charsAdded.push(1);
		}
		else if (e.getSource() == btn_del)
		{
			System.out.println("you clicked DEL button!");
			str_number.delete(str_number.length() - charsAdded.pop(), str_number.length());
			txt.setText(str_number.toString());
		}
		else if (e.getSource() == btn_equ)
		{
			double result;

			System.out.println("you clicked equal sign!");

			try
			{
				result = ExpressionEvaluation.evaluateInfixExpression(str_number.toString());

				txtHistory.setText(str_number.toString());
				txt.setText("" + result);

				str_number.delete(0, str_number.length());
				str_number.append(result);
				charsAdded.clear();
				charsAdded.push(Double.toString(result).length());
			}
			catch (Exception caught)
			{
				txtHistory.setText(str_number.toString());
				txt.setText("Error: " + caught.getMessage());
			}
		}
	}
    
	public static void main(String[] args)
	{
		new CalculatorGUI();
	}
}

