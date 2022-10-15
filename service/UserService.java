package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserService {
    static List<User> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Pattern patternEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Pattern patternPassWord = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{7,15}$");

    public void dangKy() {
        User user = new User();
        while (true) {
            System.out.println("Tên người dùng");
            String userName = sc.nextLine();
            boolean ketQua = kiemTraUserName(userName);
            if (!ketQua) {
                user.setUsername(userName);
                break;
            } else {
                System.out.println("Tài khoản đã tồn tại. Hãy thử tài khoản khác");
            }
        }

        while (true) {
            System.out.println("Nhập Email xác nhận");
            String userEmail = sc.nextLine();
            if (patternEmail.matcher(userEmail).find()) {
                user.setEmail(userEmail);
                break;
            } else {
                System.err.println("Email không hợp lệ. Hãy thử Email khác");
            }
        }

        while (true) {
            System.out.println("Nhập PassWord");
            String userPassWord = sc.nextLine();
            if (patternPassWord.matcher(userPassWord).find()) {
                user.setPassword(userPassWord);
                break;
            } else {
                System.err.println("Định dạng Password không đúng, Vui lòng nhập lại");
            }

        }
        list.add(user);

    }

    public void dangNhap() {
        while (true) {
            System.out.println("Nhập tài khoản");
            String name = sc.nextLine();
            System.out.println("Nhập mật khẩu");
            String passWord = sc.nextLine();
            if (list.isEmpty()) {
                System.err.println("Chưa có tài khoản nào. Mời bạn đăng ký trước");
                break;
            }
            for (User user : list) {
                if (name.equals(user.getUsername()) && passWord.equals(user.getPassword())) {
                    System.out.println("Đăng nhập thành công");
                    dangNhapThanhCong(user);
                    return;
                }
            }
            System.out.println("Tài khoản không tồn tại, hoặc Sai mật khẩu");
            quenMatKhau();
        }
    }
    private void quenMatKhau() {
        System.out.println("1 - Đăng nhập lại");
        System.out.println("2 - Quên mật khẩu");
        int menu = sc.nextInt();
        sc.nextLine();
        switch (menu) {
            case 1:
                break;
            case 2:
                System.out.println("Nhập Email");
                String email = sc.nextLine();
                if (patternEmail.matcher(email).find()) {
                    if (kiemTraUserEmail(email)) {
                        while (true) {
                            System.out.println("Nhập PassWord muốn thay đổi");
                            String passWord1 = sc.nextLine();
                            if (patternPassWord.matcher(passWord1).find()) {
                                for (User user : list) {
                                    if (user.getEmail().equals(email)) {
                                        user.setPassword(passWord1);
                                        break;
                                    }
                                }
                                break;
                            } else {
                                System.out.println("Định dạng Password không đúng, Vui lòng nhập lại");
                            }
                        }
                    }
                } else {
                    System.err.println("Chưa tồn tại tài khoản");
                }
        }
    }

    public void dangNhapThanhCong(User userNow) {
        System.out.println("1 - Thay đổi username");
        System.out.println("2 - Thay đổi email");
        System.out.println("3 - Thay đổi mật khẩu");
        System.out.println("4 - Đăng xuất (Sau khi đăng xuất quay về mục yêu cầu đăng nhập hoặc đăng ký)");
        System.out.println("0 - Thoát chương trình");
        int menu = sc.nextInt();
        sc.nextLine();
        switch (menu) {
            case 1:
                thayDoiUserName(userNow);
                break;
            case 2:
                thayDoiEmail(userNow);
                break;
            case 3:
                thayDoiPassWord(userNow);
                break;
            case 4:
                System.out.println("Đăng Xuât");
                return;
            case 0:
                System.out.println("Thoát chương trình");
                System.exit(0);
        }
    }
    //hàm thay đổi User Name
    public void thayDoiUserName(User userNow) {
        while (true) {
            System.out.println("Nhập User name muốn thay đổi");
            String name = sc.nextLine();
            boolean ketQua = kiemTraUserName(name);
            if (ketQua) {
                System.err.println("Tài khoản đã tồn tại. Hãy thử tài khoản khác");
            } else {
                for (User user : list) {
                    if (userNow.getUsername().equals(user.getUsername())) {
                        userNow.setUsername(name);
                        break;
                    }
                }
                break;
            }
        }
    }
    // hàm thay đổi Email
    public void thayDoiEmail(User userNow) {
        while (true) {
            System.out.println("Nhập Email muốn thay đổi");
            String email = sc.nextLine();
            if (patternEmail.matcher(email).find()) {
                if (kiemTraUserEmail(email)) {
                    System.err.println("Email đã tồn tại. Hãy thử Email khác");
                } else {
                    userNow.setEmail(email);
                    break;
                }
            } else {
                System.err.println("Email không hợp lệ. Hãy thử Email khác");
            }
        }
    }
    //hàm thay đổi password
    public void thayDoiPassWord(User userNow) {
        while (true) {
            System.out.println("Nhập PassWord muốn thay đổi");
            String passWord = sc.nextLine();
            boolean ketQua = kiemTraUserPassWord(passWord);
            if (patternPassWord.matcher(passWord).find()) {
                if (ketQua) {
                    System.err.println("Mật khẩu mới không thể trùng với mật khẩu cũ");
                } else {
                    for (User user : list) {
                        if (userNow.getPassword().equals(user.getPassword())) {
                            userNow.setPassword(passWord);
                        }
                    }
                }
                break;
            } else {
                System.err.println("Định dạng Password không đúng, Vui lòng nhập lại");
            }
        }
    }

    // các hàm kiểm tra
    private boolean kiemTraUserName(String userName) {
        for (User user : list) {
            if (userName.equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    private boolean kiemTraUserEmail(String userEmail) {
        for (User user : list) {
            if (userEmail.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }
    private boolean kiemTraUserPassWord(String userPassWord) {
        for (User user : list) {
            if (userPassWord.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }


}
