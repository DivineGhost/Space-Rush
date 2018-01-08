package GUI;

import Entities.*;
import Mechanics.*;

import java.awt.*;
import javax.swing.JFrame;

public class Window extends Canvas{

    public Window(int width, int height, String title, Game game){
        // Configura��o da JFrame relacionada à janela do jogo
        JFrame frame = new JFrame(title);
        // Fechar ao sair
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Defini��o do tamanho da janela
        Dimension d = new Dimension(width, height);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);
        frame.setResizable(false);
        // Para a janela aparecer ao centro do monitor
        frame.setLocationRelativeTo(null);
        // Deixando a janela vis�vel
        frame.setVisible(true);
        // Adicionando o jogo � janela
        frame.add(game);
        // Iniciando o jogo
        game.start();
    }    
}