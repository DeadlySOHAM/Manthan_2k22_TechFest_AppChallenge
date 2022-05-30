from apscheduler.schedulers.background import BackgroundScheduler
from .models import *
from .forms import *
from .CONSTANTS import *

def arrange():
   print('Managing')
   allData = Order.objects.filter(is_delivered=True)
   for data in allData :
      if data.is_delivered :
         d = {'order_id' : data.order_id,
            'product_id' : data.product_id,
            'product_name' : data.product_name,
            'watt' : data.watt,
            'company' : data.company,
            'amount' : data.amount,
            'quantity' : data.quantity,
            'wireLength' : data.wireLength,
            'wireColor' : data.wireColor,
            'customerName' : data.customerName,
            'customerPhNumber' : data.customerPhNumber,
            'customerAddress' : data.customerAddress,
            'customerPinCode' : data.customerPinCode,
            'customerLandMark' : data.customerLandMark,
            'is_delivered' : data.is_delivered,
            'is_cancelled' : data.is_cancelled,}
         form = DeliveredForm(d)
         print(form.save())
         data.delete()

def main() :
   schd = BackgroundScheduler()
   if DATABASE_UPDATE_DURATION_HOURS != None :
      schd.add_job(arrange,'interval',hours=DATABASE_UPDATE_DURATION_SECONDS)
   elif DATABASE_UPDATE_DURATION_MINUTES != None :
      schd.add_job(arrange,'interval',minutes=DATABASE_UPDATE_DURATION_SECONDS)
   elif DATABASE_UPDATE_DURATION_SECONDS != None :
      schd.add_job(arrange,'interval',seconds=DATABASE_UPDATE_DURATION_SECONDS)
   else :
      schd.add_job(arrange,'interval',days=DATABASE_UPDATE_DURATION_SECONDS)
   schd.start()

