import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FaalGUI extends JFrame implements ActionListener {


    private static final String GET_URL = "https://faal.spclashers.workers.dev/api";
    JButton getFaal;
    JButton backButton;
    Font customFont = null;
    Color brown = new Color(60, 54, 51);
    Color W = new Color(238, 237, 235);
    Color B = new Color(224, 204, 190);




    public FaalGUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("فال حافظ");
        this.setBackground(W);
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font/IranNastaliq.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setFont(customFont);

        HomePage();

        this.setSize(700, 800);
        this.setVisible(true);

    }

    private static Faal getFaal() throws IOException {

        URL url = new URL("https://faal.spclashers.workers.dev/api");
        Faal faal;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String content = "";
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content += (inputLine);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            faal = objectMapper.readValue(content, Faal.class);

            in.close();
            con.disconnect();
            System.out.println(content);
            return faal;
        } catch (Exception exception) {
            throw exception;
        }
    }


    public void HomePage() {

        JPanel HomePage = new JPanel();
        HomePage.setLayout(null);
        HomePage.setBackground(W);

        JLabel label2 = new JLabel("نیت کنید ...");
        label2.setForeground(brown);
        label2.setFont(customFont.deriveFont(60f));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setBounds(280, 150, 200, 150);

        JLabel label3 = new JLabel("در صورت اتمام نیت خود، روی دکمه‌ی زیر کلیک کنید.");
        label3.setForeground(brown);
        label3.setFont(customFont.deriveFont(30f));
        label3.setBounds(220, 300, 400, 50);

        getFaal = new JButton("دریافت فال");
        getFaal.setFont(customFont.deriveFont(30f));
        getFaal.setBackground(B);
        getFaal.setForeground(brown);
        getFaal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        getFaal.setBounds(250, 430, 200, 50);
        getFaal.addActionListener(this);

        HomePage.add(label2);
        HomePage.add(label3);
        HomePage.add(getFaal);

        this.add(HomePage);
    }


    public void FaalPage(String poem, String interpretation) {
        JPanel faalPage = new JPanel();
        faalPage.setBackground(W);
        faalPage.setLayout(new BoxLayout(faalPage, BoxLayout.Y_AXIS));
        faalPage.setAlignmentX(Component.CENTER_ALIGNMENT);
        faalPage.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("شعر");
        label.setForeground(brown);
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label.setFont(customFont.deriveFont(35f));
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        JPanel poemPanel = new JPanel();
        poemPanel.setLayout(new GridBagLayout());

        JTextArea label2 = new JTextArea(poem);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label2.setFont(customFont.deriveFont(25f));
        label2.setEditable(false);
        label2.setAlignmentY(Component.CENTER_ALIGNMENT);
        label2.setBackground(null);


        JLabel label3 = new JLabel("تفسیر");
        label3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setFont(customFont.deriveFont(30f));


        JTextArea label4 = new JTextArea(interpretation);
        label4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        label4.setEditable(false);
        label4.setBackground(null);
        label4.setLineWrap(true);

        label4.setFont(customFont.deriveFont(30f));


        backButton = new JButton("بازگشت");
        backButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(customFont.deriveFont(40f));

        backButton.addActionListener(this);

        faalPage.add(label);

        poemPanel.add(label2);
        faalPage.add(poemPanel);

        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        faalPage.add(label3);
        faalPage.add(Box.createRigidArea(new Dimension(0, 40)));

        faalPage.add(label4);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));

        faalPage.add(backButton);
        faalPage.add(Box.createRigidArea(new Dimension(0, 20)));


        this.add(faalPage);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getFaal) {
            this.getContentPane().removeAll();

            try {
                Faal faal = getFaal();
                faal.formatPoem();
                FaalPage(faal.getPoem(), faal.getInterpretation());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            this.revalidate();
            this.repaint();
        }

        if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            HomePage();
            this.revalidate();
            this.repaint();
        }
    }
}