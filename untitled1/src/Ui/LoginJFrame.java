package Ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    //规定:loginJFrame这个界面是登录界面
    //以后所有和登录相关的逻辑都写在这个类中
    //创建登录按钮
    JButton login = new JButton();
    //创建注册按钮
    JButton register = new JButton();
    //1.创建用户名文字
    JLabel usernameText = new JLabel();
    //2.添加用户名输入框
    JTextField username = new JTextField();
    //3.创建密码文字
    JLabel passwordText  = new JLabel();
    //4.添加密码输入框
    JPasswordField password = new JPasswordField();
    JLabel codeText  = new JLabel();
    JTextField code = new JTextField();
    JLabel rightCode = new JLabel();
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("LLQ", "123456"));
        list.add(new User("MXL", "123456"));
        list.add(new User("LOS", "123456"));
        list.add(new User("LQH", "123456"));
    }


    //构造方法,初始化
    public LoginJFrame() {
        //设置界面
        initJFrame();

        //添加组件
        initView();


        this.setVisible(true);//设置可见

    }

    private void initView() {


        usernameText.setText("用户名");
        usernameText.setBounds(130, 135, 50, 20);
        this.add(usernameText);


        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);


        passwordText.setText("密码");
        passwordText.setBounds(130, 195, 50, 20);
        this.add(passwordText);


        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);


        //验证码提示
        //5.添加验证码文字

        codeText.setText("验证码");
        codeText.setBounds(130, 260, 50, 20);
        this.add(codeText);
        //5.添加验证码输入框

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        //正确的验证码

        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);



        //6.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        //去除按钮的边框
        login.setBorderPainted(false);
        //在按钮上添加文字
        login.setText("登录");
        //去除按钮的背景
//        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);



        //7.创建注册按钮
        register.setBounds(256, 310, 128, 47);
        //去除按钮的边框
        register.setBorderPainted(false);
        //在按钮上添加文字
        register.setText("注册");
        //给登录按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\test code\\java\\untitled\\untitled1\\image\\login.jpg"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        //在创建登录界面的时候,同时给这个界面去设置一些信息总
        //比如,宽高,直接展示出来
        this.setTitle("登录界面");
        this.setSize(488,430);//设置宽高
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
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==login)
        {
            System.out.println("点击了登录");
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            System.out.println("输入的用户名："+usernameInput);
            String passwordInput = password.getText();
            System.out.println("输入的密码："+passwordInput);
            //获取用户输入的验证码
            String codeInput = code.getText();
            System.out.println("输入的验证码："+codeInput);
            //比较验证码
            if(codeInput.isEmpty())
            {
                showJDialog("验证码不能为空！");
            }
            else if(!codeInput.equals(rightCode.getText()))
            {
                System.out.println("验证码输入错误");
                showJDialog("验证码输入错误");
            }
            //判断用户名和密码是否为空,只要有一个为空就不行
            else if(username.getText().isEmpty() || password.getText().isEmpty())
            {
                System.out.println("用户名或密码不能为空！");
                showJDialog("用户名或密码不能为空！");
            }//            用户名,密码比较正确,显示登陆成功跳转游戏界面
            else if(checkUser(list, username.getText(), password.getText()))
            {
                System.out.println("登陆成功");
                showJDialog("登陆成功");
                //关闭当前界面
                this.dispose();
                //打开游戏界面
                new GameJFrame();
            }else{//            用户名,密码比较错误,提示错误
                System.out.println("登陆失败");
                showJDialog("用户名或密码错误");
                rightCode.setText(CodeUtil.getCode());
                System.out.println("因为信息错误更换验证码");
            }
        }
        else if(e.getSource() == register)
        {
            System.out.println("注册功能还未完成");
            showJDialog("注册功能还未完成");
        }else if(e.getSource() == rightCode)
        {
            rightCode.setText(CodeUtil.getCode());
            System.out.println("用户自己选择更换验证码");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }


    public boolean checkUser(ArrayList<User> list, String username, String password) {
        for (User user : list) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("用户名和密码匹配成功");
                return true;
            }
        }
        return false;
    }

}