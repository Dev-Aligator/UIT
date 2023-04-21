class Shop:
    def __init__(self, shop_id, area, revenue, cold_storage_fee=0):
        self.shop_id = shop_id
        self.area = area
        self.revenue = revenue
        self.cold_storage_fee = cold_storage_fee

    def get_rental_fee(self):
        rental_fee_per_sqm = 40000000
        return rental_fee_per_sqm * self.area

    """abstractmethod"""
    def get_tax(self):
        pass
class FoodShop(Shop):
    def __init__(self, shop_id, area, revenue, cold_storage_fee=0):
        super().__init__(shop_id, area, revenue, cold_storage_fee) 
    def get_tax(self):
        return self.revenue*0.05

class ClothingShop(Shop):
    def __init__(self, shop_id, area, revenue):
        super().__init__(shop_id, area, revenue)
    def get_tax(self):
        return self.revenue*0.1

class JewelleryShop(Shop):
    def __init__(self, shop_id, area, revenue):
        super().__init__(shop_id, area, revenue)
    def get_tax(self):
        return self.revenue * 0.2 if self.revenue <= 100000000 else self.revenue * 0.3

class OOPMarket:
    def __init__(self):
        self.shops = []

    def add_shop(self, shop):
        self.shops.append(shop)

    def get_total_rental_fee(self):
        total_rental_fee = 0
        for shop in self.shops:
            total_rental_fee += shop.get_rental_fee()
            total_rental_fee += shop.get_tax()
            total_rental_fee += shop.cold_storage_fee
        return total_rental_fee

market = OOPMarket()
n = int(input("Enter the number of shops: "))
for i in range(n):
    type = int(input("Enter the type of the shop: 1-FoodShop 2-ClothingShop 3-JewelleryShop: "))
    id = input("Enter shop id: ")
    area = float(input("Enter shop area: "))
    revenue = float(input("Enter shop revenue: "))
    if type == 1:
        cold_storage_fee = int(input("Enter cold storage fee: "))
        market.add_shop(FoodShop(id,area,revenue, cold_storage_fee))
    elif type == 2:
        market.add_shop(ClothingShop(id, area, revenue))
    elif type==3:
        market.add_shop(JewelleryShop(id,area, revenue))
    else:
        print("Invalid Shop Type ~ Exiting")
        exit(0)

print("Total rental fee: ", market.get_total_rental_fee())




