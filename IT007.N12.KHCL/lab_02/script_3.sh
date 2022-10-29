read -p "Enter n: " n
while [ $n -lt 10 ]; do
    read -p "Try with n greater than 10: " n
done
i=1
sum=0
while [ $i -le $n ]
do
    sum=$(($sum + $i))
    i=$((i+1))
done
echo $sum

