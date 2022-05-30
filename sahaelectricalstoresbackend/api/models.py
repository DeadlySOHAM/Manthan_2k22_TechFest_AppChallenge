from django.db import models

# Create your models here.

class Order(models.Model) :
   order_id = models.CharField(max_length=20,default=None)
   product_id = models.SmallIntegerField(default=None)
   product_name = models.CharField(max_length=25,default=None)
   watt = models.FloatField(default=None)
   company = models.CharField(max_length=15,default=None)
   amount = models.FloatField(default=None)
   quantity = models.SmallIntegerField(default=None)
   wireLength = models.FloatField(default=None)
   wireColor = models.CharField(max_length=10 ,default=None)
   customerName = models.CharField(max_length=15,default=None)
   customerPhNumber = models.CharField(max_length=15,default=None)
   customerAddress = models.CharField(max_length=25,default=None)
   customerPinCode = models.CharField(max_length=7,default=None)
   customerLandMark = models.CharField(max_length=25,default=None)
   is_delivered = models.BooleanField(default=False)
   is_cancelled = models.BooleanField(default=False)
   class Meta:
      db_table="Order"


class Delivered(models.Model) :
   order_id = models.CharField(max_length=20,default=None)
   product_id = models.SmallIntegerField(default=None)
   product_name = models.CharField(max_length=25,default=None)
   watt = models.FloatField(default=None)
   company = models.CharField(max_length=15,default=None)
   amount = models.FloatField(default=None)
   quantity = models.SmallIntegerField(default=None)
   wireLength = models.FloatField(default=None)
   wireColor = models.CharField(max_length=10 ,default=None)
   customerName = models.CharField(max_length=15,default=None)
   customerPhNumber = models.CharField(max_length=15,default=None)
   customerAddress = models.CharField(max_length=25,default=None)
   customerPinCode = models.CharField(max_length=7,default=None)
   customerLandMark = models.CharField(max_length=25,default=None)
   is_delivered = models.BooleanField(default=False)
   is_cancelled = models.BooleanField(default=False)
   class Meta:
      db_table="Delivered"


class Cancelled(models.Model) :
   order_id = models.CharField(max_length=20,default=None)
   product_id = models.SmallIntegerField(default=None)
   product_name = models.CharField(max_length=25,default=None)
   watt = models.FloatField(default=None)
   company = models.CharField(max_length=15,default=None)
   amount = models.FloatField(default=None)
   quantity = models.SmallIntegerField(default=None)
   wireLength = models.FloatField(default=None)
   wireColor = models.CharField(max_length=10 ,default=None)
   customerName = models.CharField(max_length=15,default=None)
   customerPhNumber = models.CharField(max_length=15,default=None)
   customerAddress = models.CharField(max_length=25,default=None)
   customerPinCode = models.CharField(max_length=7,default=None)
   customerLandMark = models.CharField(max_length=25,default=None)
   is_delivered = models.BooleanField(default=False)
   is_cancelled = models.BooleanField(default=False)
   class Meta:
      db_table="Cancelled"

