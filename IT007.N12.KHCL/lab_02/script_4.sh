read -p "Nhap chuoi: ";
if grep -q ${REPLY} *.txt ; then
    echo "String found !!"
else 
    echo "No such file contains {$REPLY} in current directory" 
fi
exit 0
