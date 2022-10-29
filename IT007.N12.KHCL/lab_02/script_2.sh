read -p "Nhap MSSV: " mssv
while [ $((mssv)) -ne 21521413 ]; do
    read -p "Sorry, try again: " mssv
done
echo $((mssv))
exit 0
