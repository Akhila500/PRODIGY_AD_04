import java.awt.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    private final JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;
    private final JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 470);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(128, 0, 128)); 

        
        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

       
        JPanel grid = new JPanel(new GridLayout(3, 3, 5, 5));
        grid.setBackground(new Color(128, 0, 128));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font font = new Font("Segoe UI", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                btn.setFocusPainted(false);
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
                btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

                int row = i, col = j;
                btn.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = btn;
                grid.add(btn);
            }
        }

        add(grid, BorderLayout.CENTER);

        
        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resetButton.setFocusPainted(false);
        resetButton.setBackground(new Color(230, 230, 250)); 
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resetButton.addActionListener(e -> resetGame());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(128, 0, 128));
        bottomPanel.add(resetButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleMove(int row, int col) {
        JButton btn = buttons[row][col];
        if (!btn.getText().equals("")) return;

        String symbol = xTurn ? "X" : "O";
        btn.setText(symbol);
        btn.setForeground(xTurn ? Color.BLUE : Color.RED); 

        if (checkWin()) {
            statusLabel.setText(symbol + " Wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a Draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText((xTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWin() {
        String symbol = xTurn ? "X" : "O";
        for (int i = 0; i < 3; i++) {
            if (symbol.equals(buttons[i][0].getText()) &&
                symbol.equals(buttons[i][1].getText()) &&
                symbol.equals(buttons[i][2].getText())) return true;
            if (symbol.equals(buttons[0][i].getText()) &&
                symbol.equals(buttons[1][i].getText()) &&
                symbol.equals(buttons[2][i].getText())) return true;
        }
        if (symbol.equals(buttons[0][0].getText()) &&
            symbol.equals(buttons[1][1].getText()) &&
            symbol.equals(buttons[2][2].getText())) return true;
        if (symbol.equals(buttons[0][2].getText()) &&
            symbol.equals(buttons[1][1].getText()) &&
            symbol.equals(buttons[2][0].getText())) return true;

        return false;
    }

    private boolean isDraw() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                if (btn.getText().equals("")) return false;
        return true;
    }

    private void disableButtons() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setEnabled(false);
    }

    private void resetGame() {
        for (JButton[] row : buttons)
            for (JButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
                btn.setForeground(Color.BLACK);
            }
        xTurn = true;
        statusLabel.setText("X's Turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
