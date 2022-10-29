
## 21521413 - Lab 01
#

1. Chuyển về thư mục gốc:

        ~
![image](Figs/1_1.png)

   Chuyển đến thư mục /bin

        cd /bin

![image](Figs/1_2.png)

Chuyển dến thư mục người dùng

        cd /usr

![image](Figs/1_3.png)

2. Tạo cây thư mục

        mkdir Myweb 
        cd Myweb
        mkdir images databases scripts java
        cd images
        mkdir icon background animation

![image](Figs/2.png)
        

3.
    Tìm files với extension

        find . -type f \( -name "*.html" -o -name "*.class" \)

![image](Figs/3_1.png)

Copy file đuôi html đến thư mục Myweb
        
        cp ~/Code/javascript/webDev/html/index.html Myweb


![image](Figs/3_2.png)

Di chuyển file từ thư mục Myweb đến Myweb/java

        mv index.html java

![image](Figs/4.png)

5. Thực hiện xóa thư mục Myweb/scripts

        rmdir scripts

![image](Figs/5.png)

6. Thiết lập quyền truy xuất cho thư mục Myweb/databases

        chmod 700 databases/

7. Tạo group hdh

        sudo groupadd hdh
![image](Figs/7.png)

Tạo user

        sudo useradd --badname 21521413 -g hdh

![image](Figs/7_1.png)