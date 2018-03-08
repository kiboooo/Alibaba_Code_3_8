import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 编译器版本: Java 1.8.0_66
 请使用标准输入输出(System.in, System.out)；已禁用图形、文件、网络、系统相关的操作，
 如java.lang.Process , javax.swing.JFrame , Runtime.getRuntime；不要自定义包名称，否则会报错，
 即不要添加package answer之类的语句；您可以写很多个类，
 但是必须有一个类名为Main，并且为public属性，并且Main为唯一的public class，
 Main类的里面必须包含一个名字为'main'的静态方法（函数），这个方法是程序的入口
 时间限制: 3S (C/C++以外的语言为: 5 S)   内存限制: 128M (C/C++以外的语言为: 640 M)
 输入:
 第一行表示文件数n 接下来的n行里面第m行表示第m个文件依赖的文件，多个用英文逗号隔开，如果没有则用0表示
 输出:
 输出循环依赖的文件列表，按顺序从小到大排序，用英文逗号隔开，多个循环列表则分多行展示。如果没有则输出0
 输入范例:
 3
 2,3
 3
 1
 输出范例:
 1,2,3

 化简题意就相当于找无向图中有多少条回路；
 利用DFC算法解决
 */
public class Main {
    private static boolean Targe = false;
    private static int n ;
    private static ArrayList<Integer> arrayList = new ArrayList<>() ;

    public static void find(int[][] a, int[] q, int x) {
        if (q[x] == 1) {
            int j;
            if ((j = arrayList.indexOf(x)) != -1) {
                Targe = true;
                while (j < arrayList.size() ) {
                    if (j==arrayList.size()-1)
                        System.out.println(arrayList.get(j));
                    else
                        System.out.print(arrayList.get(j)+",");
                    j++;
                }
                return;
            }
            return;
        }
        q[x] = 1;
        arrayList.add(x);
        for (int i = 1; i < n+1; i++) {
            if (a[x][i] == 1) {
                find(a, q, i);
            }
        }
        arrayList.remove(arrayList.size() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] files = new int[n+1][n+1];
        int[] in = new int[n + 1];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(files[i], 0);
        }

        Arrays.fill(in, 0);
        for (int i = 1; i < n + 1; i++) {
            String[] s = sc.next().split(",");
            if (s.length > 0) {
                for (int i1 = 0; i1 < s.length; i1++) {
                    files[i][Integer.parseInt(s[i1])] = 1;
                }
            }
        }
        find(files, in, 1);
        if (!Targe) {
            System.out.println("0");
        }
    }


}
