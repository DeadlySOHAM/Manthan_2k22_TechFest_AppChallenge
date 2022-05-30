from django.shortcuts import render

# Create your views here.
import ast
from django.http import *
from django.views.decorators.csrf import csrf_exempt
from .models import *
from .forms import *
from .mail import *
from .utils import *

@csrf_exempt
def order(request):
   data = ast.literal_eval(request.body.decode('UTF-8'))
   form = OrderForm(data)
   if form.is_valid() == False :
      return JsonResponse({'status':False})
   form.save()
   sendMail(data['order_id'],String(data))
   return JsonResponse({'status':True})
