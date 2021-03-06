package gui;

import figures.Figure;
import figures.Point;
import figures.Rhombus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;

public class CreateRhombus {

    MyFrame frame;
    Rhombus r;
    JButton button;

    public CreateRhombus() {
    }

    void draw(Deque<Figure> figures, Deque<ActionInfo> actions, Graphics g, App.MyPanel canvas) {
        frame = new MyFrame("Создать ромб");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel xCoordinate = new JLabel("Абсцисса центра: ");
        JTextField xCoordinateField = new JTextField(8);

        JLabel yCoordinate = new JLabel("Ордината центра: ");
        JTextField yCoordinateField = new JTextField(8);

        JLabel firstDiagonal = new JLabel("Первая диагональ: ");
        JTextField firstDiagonalField = new JTextField(8);

        JLabel secondDiagonal = new JLabel("Вторая диагональ: ");
        JTextField secondDiagonalField = new JTextField(8);

        JLabel theta = new JLabel("Угол поворота: ");
        JTextField thetaField = new JTextField(8);

        button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xCoordinateText = xCoordinateField.getText();
                String yCoordinateText = yCoordinateField.getText();
                String firstDiagonalText = firstDiagonalField.getText();
                String secondDiagonalText = secondDiagonalField.getText();
                String thetaText = thetaField.getText();
                if (xCoordinateText.equals("") || yCoordinateText.equals("") || firstDiagonalText.equals("")
                        || secondDiagonalText.equals("") || thetaText.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Заполните все поля");
                    return;
                }
                String template = "((-|\\+)?[0-9]+(\\.[0-9]+)?)+";
                if (!xCoordinateText.matches(template)) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: введённая абсцисса центра не является числом");
                    return;
                }
                if (!yCoordinateText.matches(template)) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: введённая ордината центра не является числом");
                    return;
                }
                if (!firstDiagonalText.matches(template)) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: введённая длина первой диагонали не является числом");
                    return;
                }
                if (!secondDiagonalText.matches(template)) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: введённая длина второй диагонали не является числом");
                    return;
                }
                if (!thetaText.matches(template)) {
                    JOptionPane.showMessageDialog(frame, "Ошибка: введённый угол поворота не является числом");
                    return;
                }
                r = new Rhombus(new Point(Double.parseDouble(xCoordinateText), Double.parseDouble(yCoordinateText)),
                        Double.parseDouble(firstDiagonalText), Double.parseDouble(secondDiagonalText), Double.parseDouble(thetaText));
                g.setColor(Color.black);
                r.draw(g);
                figures.push(r);
                canvas.repaint();
                actions.push(new ActionInfo(ACTION_TYPE.ADD, null));
                frame.setVisible(false);
            }
        });

        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contents.add(xCoordinate);
        contents.add(xCoordinateField);
        contents.add(yCoordinate);
        contents.add(yCoordinateField);
        contents.add(firstDiagonal);
        contents.add(firstDiagonalField);
        contents.add(secondDiagonal);
        contents.add(secondDiagonalField);
        contents.add(theta);
        contents.add(thetaField);
        contents.add(button);

        frame.setContentPane(contents);
        frame.pack();
        frame.setVisible(true);
    }

    static class MyFrame extends JFrame {
        public MyFrame(String title) {
            super(title);
        }
    }

}
