import service.User;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);
        int menu;
        while (true) {
            System.out.println("Mời bạn chọn lựa danh mục: ");
            System.out.println("1 - Đăng nhập");
            System.out.println("2 - Đăng ký");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    userService.dangNhap();
                    break;
                case 2:
                    System.out.println("Đăng Ký");
                    userService.dangKy();
                    break;
            }
        }
    }
}