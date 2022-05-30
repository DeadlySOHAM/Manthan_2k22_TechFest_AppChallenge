from django import forms
from .models import *

class OrderForm(forms.ModelForm):
   class Meta :
      model = Order
      fields = "__all__"

class DeliveredForm(forms.ModelForm):
   class Meta :
      model = Delivered
      fields = "__all__"