package com.whjlim.didi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BlackWatch on 2019/8/27 18:51
 */
public class Main {

    /**
    *@Author: whjLim
    *@Date: 19:42 2019/8/27
     * 算式转移
    6
    3 + 2 + 1 + -4 * -5 + 1
    *
    */
    boolean[][] op = new boolean[256][256];
    void init(){
        op['+']['+'] = true;
        op['+']['-'] = true;
        op['-']['+'] = true;
        op['-']['-'] = true;
        op['*']['*'] = true;
        op['*']['/'] = true;
        op['/']['*'] = true;
        op['/']['/'] = true;
    }
    public void modifyFunction(){
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        String funString = sc.nextLine();
        if(n == 1){
            System.out.println(funString);
        }else{
            init();
            String[] temp = funString.split(" ");
            int[] variable = new int[n];
            String[] operate = new String[n-1];
            for(int i = 0,j = 0, k = 0, len = temp.length; i < len; i++){
                if(i % 2 == 0){//变量
                    variable[j++] = Integer.valueOf(temp[i]);
                }else{//操作符
                    operate[k++] = temp[i];
                }
            }
            //遍历运算符，做合并判断
            int index = 0, top = 0; // 当前运算符位置
            int opNum = n-1; //操作符个数
            boolean first = true;
            boolean befor, after; // 操作符的左右两方
            StringBuilder sb = new StringBuilder();
            for(int i = 0;  i < opNum; i++){
                int comNum = 0; //本次可运算的操作数量，起码为1，当前为
                index = i;    //判定下一个运算符能不能被加进来
                while (index+1 < opNum && op[operate[index].charAt(0)][operate[index+1].charAt(0)]){
                    index++;
                    comNum++;
                }
                if(comNum == 0){//当前运算符不能向下，有两种情况 1+2*3
                    sb.append(variable[top++]);
                    sb.append(" " + operate[i]);
                }else{ //能向下运算
                    int[] changNum = new int[comNum+1]; //参与本次运算的变量
                    int k = 0;
                    boolean flag = false;
                    for(int j = 0;  j < comNum; j++){
                        if(!flag){
                            flag = true;
                            changNum[k++] = variable[top++];
                            changNum[k++] = variable[top++];
                        }else{
                            changNum[k++] = variable[top++];
                        }
                    }
                    Arrays.sort(changNum);
                    if(first){
                        first = false;
                    }else{
                        sb.append(" ");
                    }
                    sb.append(changNum[0]);
                    k = 1;
                    for(; i < index; i++){
                        sb.append(" ");
                        sb.append(operate[i]);
                        sb.append(" ");
                        sb.append(changNum[k++]);
                    }
                    sb.append(" ");
                    sb.append(operate[index]);
                }
            }
            System.out.println(sb.toString());
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.modifyFunction();
    }
}
