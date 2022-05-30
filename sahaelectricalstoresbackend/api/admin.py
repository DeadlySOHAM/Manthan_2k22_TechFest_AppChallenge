from django.contrib import admin

# Register your models here.
from .models import *
from django.forms import *




class OrderAdminView(admin.ModelAdmin):
   list_display = ['product_name','company','amount','quantity','is_delivered','is_cancelled']


admin.site.register(Order,OrderAdminView)
admin.site.register(Delivered,OrderAdminView)