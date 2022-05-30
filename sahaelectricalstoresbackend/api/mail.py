from sahaelectricalstoresbackend.settings import *
from django.core.mail import send_mail


def sendMail(order_id,data):
   try :
      send_mail(
      order_id,# subject
      data,# body
      EMAIL_HOST_USER, 
      [EMAIL_HOST_USER], 
      )
      print('sent')
      return x
   except Exception as e:
      print(e)
      pass