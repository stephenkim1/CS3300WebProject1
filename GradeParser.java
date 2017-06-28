/*
 * Java application which takes the published Median Grades .pdf data (after 
 * course name removed) and parses it into .csv-compatible text.
 * By Mark Ren
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

class GradeParser extends JFrame {
	
	public GradeParser() {
		super("Median Grade Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JTextArea inputArea = new JTextArea(100, 10);
		JButton subjectButton = new JButton("Course Code");
		JButton gradeButton = new JButton("Size/Grade");
		
		subjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArea.setText(subjectNumber(inputArea.getText()));
			}
		});
		gradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputArea.setText(classGrade(inputArea.getText()));
			}
		});
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(subjectButton);
		buttonPanel.add(gradeButton);
		mainPanel.add(inputArea);
		mainPanel.add(buttonPanel);
		
		mainPanel.setPreferredSize(new Dimension(400, 400));
		
		add(mainPanel);
		
		pack();
		setVisible(true);
	}
	
	public String subjectNumber(String input) {
		input = processString(input);
		if(input.length() == 0) {
			System.out.println("Please enter a nonempty String next time.");
			return "";
		}
		if(input.charAt(0) != '\"')
			input = '\"' + input;
		for(int i = 1; i < input.length(); i++) {
			if(input.charAt(i) == ' ') {
				if(i == input.length() - 1)
					input = input.substring(0, i) + '\"';
				else if(input.charAt(i + 1) == ' ') {
					input = input.substring(0, i) + input.substring(i + 1);
					i--;
				}
				else if(input.charAt(i + 1) == '\n') {
					input = input.substring(0, i) + input.substring(i + 1);
					i--;
				}
				else if(Character.isLetter(input.charAt(i + 1))) {
					input = input.substring(0, i) + "\"\n\"" + input.substring(i + 1);
				}
				else if(Character.isDigit(input.charAt(i + 1))) {
					input = input.substring(0, i) + "\",\"" + input.substring(i + 1);
				}
				else {
					System.out.println("Error: unrecognized character at index: " + i);
				}
			}
		}
		if(input.charAt(input.length() - 1) != '\"') {
			input = input + "\"";
		}
		
		return input;
	}
	
	public String classGrade(String input) {
		input = processString(input);
		if(input.length() == 0) {
			System.out.println("Please enter a nonempty String next time.");
			return "";
		}
		if(input.charAt(0) != '\"')
			input = '\"' + input;
		for(int i = 1; i < input.length(); i++) {
			if(input.charAt(i) == ' ') {
				if(i == input.length() - 1)
					input = input.substring(0, i) + '\"';
				else if(input.charAt(i + 1) == ' ') {
					input = input.substring(0, i) + input.substring(i + 1);
					i--;
				}
				else if(input.charAt(i + 1) == '\n') {
					input = input.substring(0, i) + input.substring(i + 1);
					i--;
				}
				else if(Character.isLetter(input.charAt(i + 1))) {
					input = input.substring(0, i) + "\",\"" + input.substring(i + 1);
				}
				else if(Character.isDigit(input.charAt(i + 1))) {
					input = input.substring(0, i) + "\"\n\"" + input.substring(i + 1);
				}
				else {
					System.out.println("Error: unrecognized character at index: " + i);
				}
			}
		}
		if(input.charAt(input.length() - 1) != '\"') {
			input = input + "\"";
		}
		
		return input;
	}
	
	public String processString(String input) {
		input = input.trim();
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '\n') {
				input = input.substring(0, i) + " " + input.substring(i + 1);
			}
		}
		return input;
	}
	
	public static void main(String[] args) {
		
		new GradeParser();
		/*Scanner scan = new Scanner(new BufferedInputStream(System.in));
		System.out.println("Enter string from Median Grade .pdf");
		String input = "";
		String next = scan.nextLine();
		System.out.println("Next = " + next);
		while(next.length() > 0) {
			input += " " + next;
			next = scan.nextLine();
		}
		
		input = input.trim();
		if(input.length() == 0) {
			System.out.println("Please enter a nonempty String next time.");
			return;
		}
				
		//System.out.println(subjectNumber(input));
		System.out.println(classGrade(input));*/
	}
}