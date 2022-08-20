import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;


class NextPage extends Template implements ActionListener
{
    JLabel label;
    JButton pack,unpack;

    NextPage(String value)
    {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        label = new JLabel("WELCOME: ");
        Dimension size = label.getPreferredSize();
        label.setBounds(40,50,size.width + 60, size.height);
        label.setFont(new Font("Century",Font.BOLD,17));
        label.setForeground(Color.red);

        pack = new JButton("Pack Files");
        Dimension bsize =pack.getPreferredSize();
        pack.setBounds(100, 100, bsize.width, bsize.height);
        pack.addActionListener(this);

        unpack = new JButton("Unpack Files");
        Dimension b2size = unpack.getPreferredSize();
        unpack.setBounds(300, 100, b2size.width+10, b2size.height);
        unpack.addActionListener(this);

        _header.add(label);
        _content.add(pack);
        _content.add(unpack);

        ClockHome();

        this.setSize(500,500);
        this.setResizable(true); //////////////*******//////////////////////// */
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==exit)
        {
            this.setVisible(false);
            System.exit(0);
        }

        if(ae.getSource()==minimize)
        {
            this.setState(this.ICONIFIED);
        }

        if(ae.getSource()==pack)
        {
            this.setVisible(false);

            try
            {
                MarvellousPackFront obj = new MarvellousPackFront();  // when wepress the pack button it will create object of marvellouspackfront
            }
            catch(Exception e){}
        }

        if (ae.getSource() == unpack)
        {
            this.setVisible(false);

             MarvellousUnpackFront obj = new MarvellousUnpackFront();

        }
        
    }

}
    

