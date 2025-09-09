package Ui;

import java.util.Random;

public class CodeUtil {
    public static String getCode()
    {

        Random random = new Random();
        // 设置一个数组,包含所有字母的大小写
        char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        // 设置一个数组,包含所有数字
        char[] ch1 = {'0','1','2','3','4','5','6','7','8','9'};
        // 创建长度为4的字符数组用于存储验证码
        char[] ch2 = new char[5];

        // 填充随机字母
        for (int i = 0; i < 5; i++) {
            ch2[i] = ch[random.nextInt(ch.length)];
        }

        // 随机选择一个位置插入数字
        int index = random.nextInt(ch2.length);
        char num = ch1[random.nextInt(ch1.length)];
        ch2[index] = num;

        // 把ch2转换成字符串
        String str = new String(ch2);

        System.out.println("生成的验证码：" + str);
        return str;
    }
}
