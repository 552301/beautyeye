package org.jb2011.inf.beautyeye;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * @author zhoujinming
 * @Classname Test
 * @Description TODO
 * @Date 2020-01-09 15:01
 * @Created by zhoujinming
 */
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch14_combox.BEComboBoxUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class Test {

    public Test() {
        initialize();
    }

    private JFrame frame;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test window = new Test();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame = new JFrame("Java练习");
        frame.setBounds(100, 100, 516, 367);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("测试");
        label.setFont(new Font("华文隶书", Font.PLAIN, 19));
        label.setBounds(10, 39, 95, 19);
        frame.getContentPane().add(label);

        JButton button = new JButton("点击");
        button.setText("点击");
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        button.setBounds(80, 99, 95, 30);
        frame.getContentPane().add(button);

        JComboBox comboBox = new JComboBox();
        comboBox.setToolTipText("测试");
        comboBox.setBounds(140, 149, 195, 30);
        comboBox.setUI(new BEComboBoxUI());
        frame.getContentPane().add(comboBox);
    }

}