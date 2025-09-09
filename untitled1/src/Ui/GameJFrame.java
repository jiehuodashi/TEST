package Ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int BuSHU = 0;
    //JFrame 界面 窗体
    //JFrame的子类也一样
    //规定:GameJFrame这个界面是游戏界面
    //以后所有和游戏相关的逻辑都写在这个类中
    // 管理数据
    int[][] data = new int[3][3];
    // 记录空白方块在二维数组的位置
    int x = 0;
    int y = 0;
    // 存储正确的数据
    int[][] win = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
    };

    //创建选项下面的条目对象
    JMenuItem replayJMenuItem = new JMenuItem("重新游戏");
    JMenuItem reLoginJMenuItem = new JMenuItem("重新登录");
    JMenuItem closeJMenuItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    //定义一个变量,记录当前展示图片的路径
    String path = "D:\\test code\\java\\untitled\\untitled1\\image\\LLQ\\";

    //


    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        // 初始化数据
        initDate();

        //初始化图片
        initImage();


        this.setVisible(true);//设置可见
    }

    private void initDate() {
        int [] temp = {0,1,2,3,4,5,6,7,8};
        Random random = new Random();
        for (int i = 0; i < temp.length; i++) {
            int index = random.nextInt(temp.length);
            int temp1 = temp[i];
            temp[i] = temp[index];
            temp[index] = temp1;
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                x = i / 3;
                y = i % 3;
                // 空白方块的位置
            }
            //i=0  0 0
            //i=1  0 1
            //i=2  0 2
            //i=3  1 0
            //i=4  1 1
            //i=5  1 2
            //i=6  2 0
            //i=7  2 1
            //i=8  2 2
            data[i / 3][i % 3] = temp[i];// 二维数组赋值
        }

    }

    //添加图片的时候,就需要按照二维数组中管理的数据添加图片
    private void initImage() {
        //清空已经出现的图片
        this.getContentPane().removeAll();

        if(win())
        {
            JLabel win = new JLabel(new ImageIcon("D:\\test code\\java\\untitled\\untitled1\\image\\胜利.jpg"));
            win.setBounds(354, 393, 105, 105);
            this.getContentPane().add(win);
        }

        JLabel stepCout  = new JLabel("步数：" + BuSHU);
        stepCout.setBounds(10, 10, 100, 50);
        this.getContentPane().add(stepCout);


        //加载图片
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int number = data[i][j];
                //创建一个ImageIcon对象，参数为图片路径
                //ImageIcon bg = ;
                //创建一个Label对象，参数为ImageIcon对象(管理容器)
                JLabel label = new JLabel(new ImageIcon(path+"split_"+number+".png"));

                //指定图片位置
                label.setBounds(105*j+144,105*i+183,105,105);

                //给图片添加边框
                //0:LOWERED:凸边
                //1:RAISED:凹边
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));

                //获取contentPane
                this.getContentPane().add(label);
            }

        }

        //添加背景图片
        ImageIcon bg = new ImageIcon("D:\\test code\\java\\untitled\\untitled1\\image\\bj.png");
        JLabel bgLabel = new JLabel(bg);//创建一个标签
        bgLabel.setBounds(0,0,603,680);//设置图片位置
        this.getContentPane().add(bgLabel);//添加

        //刷新界面
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上两个选项的对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changeJMenu = new JMenu("更换图片");



        //将每一个选项下面的条目添加到选项中
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(changeJMenu);
        functionJMenu.add(reLoginJMenuItem);
        functionJMenu.add(closeJMenuItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayJMenuItem.addActionListener(this);
        reLoginJMenuItem.addActionListener(this);
        closeJMenuItem.addActionListener(this);
        accountItem.addActionListener(this);

        //将菜单添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //将菜单栏添加到界面中
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置宽高
        this.setSize(603,680);
        //设置标题
        this.setTitle("拼图游戏单鸡版");
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

        //取消默认的居中放置,只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //添加键盘监听
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 65) // a
        {
            //把图片全部删除
            this.getContentPane().removeAll();


            //加载完整图片
            JLabel all = new JLabel(new ImageIcon(path+"LLQ.jpg"));
            all.setBounds(144,183,315,315);
            this.getContentPane().add(all);

            //添加背景图片
            ImageIcon bg = new ImageIcon("D:\\test code\\java\\untitled\\untitled1\\image\\bj.png");
            JLabel bgLabel = new JLabel(bg);//创建一个标签
            bgLabel.setBounds(0,0,603,680);//设置图片位置
            this.getContentPane().add(bgLabel);//添加




            //刷新界面
            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(win())
        {
            JOptionPane.showMessageDialog(this,"恭喜你，通过游戏！");
            return;
        }
        //上:38
        //下:40
        //左:37
        //右:39
        int code = e.getKeyCode();
        System.out.println("按键值："+code);
        if (code == 39) {


            if(y == 2)//空白在最右边,不能再向右移动
            {
                return;
            }
            BuSHU++;
            System.out.println("步数:"+BuSHU);
            System.out.println("向右移动");
            //把空白块的右方的数字移动向右移动
            //x y+1 表示移动的数字
            data[x][y] = data [x][y+1];
            data[x][y+1] = 0;
            y++;
            initImage();
        }else if (code == 37) {

            if(y == 0)
            {
                return;
            }
            BuSHU++;
            System.out.println("步数:"+BuSHU);
            System.out.println("向左移动");
            //把空白块的左方的数字移动向左移动
            //x y-1
            data[x][y] = data [x][y-1];
            data[x][y-1] = 0;
            y--;
            initImage();

        }else if (code == 38) {

            if(x == 0)
            {
                return;
            }
            BuSHU++;
            System.out.println("步数:"+BuSHU);
            System.out.println("向下移动");
            //把空白块的上方的数字移动向下移动
            //x-1 y 表示移动的数字
            data[x][y] = data [x-1][y];
            data[x-1][y] = 0;
            x--;
            initImage();

        }else if (code == 40) {

            if(x == 2)
            {
                return;
            }
            BuSHU++;
            System.out.println("步数:"+BuSHU);
            System.out.println("向上移动");
            //把空白块的下方的数字移动向上移动
            //x+1 y 表示移动的数字
            data[x][y] = data [x+1][y];
            data[x+1][y] = 0;
            x++;
            initImage();
        }else if(code == 65)
        {
            initImage();
        }
        else if(code == 87)//按w键作弊
        {
//            data =win;
            // 将当前数据设置为胜利状态
            for (int i = 0; i < win.length; i++) {
                for (int j = 0; j < win[i].length; j++) {
                    data[i][j] = win[i][j];
                }
            }
            // 更新空白方块位置到右下角
            x = 2;
            y = 2;
            //重新绘制界面
            initImage();
        }

    }

    public boolean win() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object source = e.getSource();
        if (source == replayJMenuItem) {
            System.out.println("重新游戏");
            BuSHU = 0;
            initDate();
            initImage();
        }else if (source == reLoginJMenuItem) {
            System.out.println("点击了重新登录");

            //关闭当前界面
            this.dispose();
//            作用：释放当前窗口及其所有资源
//            效果：彻底关闭窗口，释放内存资源
//            特点：窗口将从屏幕上消失，并且释放与之相关的系统资源
//            this.setVisible(false);
//            作用：设置窗口可见性为false
//            效果：隐藏窗口但不释放资源
//            特点：窗口仍然存在于内存中，只是不可见
            //创建登录界面
            new LoginJFrame();
        }else if (source == closeJMenuItem) {
            System.out.println("点击了关闭游戏");
            System.exit(0);
        }else if (source == accountItem) {
            System.out.println("点击了公众号");
            JOptionPane.showMessageDialog(this,"滚蛋!!!LLQ");

//            //创建一个弹框对象
//            JDialog jDialog = new JDialog();
////创建一个管理图片的容器对象JLabel
//            JLabel jlabel = new JLabel(new ImageIcon( "about.png"));
////设置位置和宽高
//            jlabel.setBounds(  0,  0,  258,  258);
////把图片添加到弹框当中
//            jDialog.getContentPane().add(jlabel);
////给弹框设置大小
//            jDialog.setSize( 344,  344);
////让弹框置顶
//            jDialog.setAlwaysOnTop(true);
////让弹框居中
//            jDialog.setLocationRelativeTo(null);
////弹框不关闭则无法操作下面的界面
//            jDialog.setModal(true);
////让弹框显示出来
//            jDialog.setVisible(true);

        }
    }
}
