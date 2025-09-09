package Ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //规定:RegisterJFrame这个界面是注册界面
    //以后所有和注册相关的逻辑都写在这个类中
    public RegisterJFrame()
    {
        this.setTitle("注册界面");
        this.setSize(488,500);//设置宽高
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        /*
            /**
             * 不执行任何作的默认窗口关闭作。
             * /
            public static final int DO_NOTHING_ON_CLOSE = 0;
            /**
             * hide-window 默认窗口关闭作
             * /
            public static final int HIDE_ON_CLOSE = 1;
            /**
             * dispose-window 默认窗口关闭作。//最后一个窗口关闭时，关闭应用程序。(每一个窗口都要加上这个)
            public static final int DISPOSE_ON_CLOSE = 2;
           /**
             * 退出应用程序默认窗口关闭作。
            public static final int EXIT_ON_CLOSE = 3;
*/
        //关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//点×退出程序

        this.setVisible(true);//设置可见
    }
}
